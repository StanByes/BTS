<?php

namespace App\Http\Controllers\Users;
use App\Http\Controllers\Controller;
use App\Models\ResetPasswordQuery;
use App\Models\User;
use Database\Factories\UserFactory;
use Illuminate\Contracts\View\Factory;
use Illuminate\Contracts\View\View;
use Illuminate\Foundation\Application;
use Illuminate\Http\RedirectResponse;
use Illuminate\Http\Request;
use Illuminate\Routing\Redirector;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Mail;

class UserController extends Controller {
    public function login(Request $request): Factory|View|Redirector|RedirectResponse
    {
        if ($request->isMethod("post")) {
            $data = $request->validate([
                'mail' => 'required|exists:users,mail',
                'password' => 'required'
            ]);

            if (Auth::attempt($data)) {
                $request->session()->regenerate();
                return redirect(route('home'));
            }

            return back();
        }

        return view("users.login");
    }

    public function logout(Request $request): Application|Redirector|RedirectResponse
    {
        Auth::logout();

        $request->session()->invalidate();
        $request->session()->regenerateToken();

        return redirect('/');
    }

    public function update(Request $request): Application|Redirector|RedirectResponse
    {
        $userData = $request->validate([
            'firstname' => 'string|required',
            'surname' => 'string|required',
            'mail' => 'email|required'
        ]);
        $internshipData = $request->validate([
            'start_at' => 'date|required',
            'end_at' => 'date|required',
            'day_start_at' => 'date_format:H:i|nullable',
            'day_end_at' => 'date_format:H:i|nullable',
        ]);

        $user = self::getUser();
        $user->update($userData);
        $user->internship->update($internshipData);

        return redirect('/');
    }

    public function resetPasswordQuery(Request $request): View|Application|Factory|Redirector|RedirectResponse
    {
        if ($request->isMethod('post')) {
            $data = $request->validate([
                'mail' => 'email|required'
            ]);

            $user = User::where('mail', '=', $data['mail'])->first();
            if (!isset($user)) {
                return back()->with('flash', [
                    'error' => true,
                    'message' => 'Adresse mail introuvable'
                ]);
            }

            $code = str()->random(20);
            $query = $user->resetPasswordQuery()->create(['code' => $code]);

            Mail::to($user->mail)->send(new \App\Mail\ResetPasswordQuery($query));
            return redirect(route('login'))->with('flash', [
                'error' => false,
                'message' => 'Mail envoyé avec succès'
            ]);
        }

        return view('users.reset_password_query');
    }

    public function resetPassword(Request $request)
    {
        if (empty($request->query('code'))) {
            return redirect(route('home'));
        }

        $code = $request->query('code');
        $resetPasswordQuery = ResetPasswordQuery::where('code', '=', $code)->first();
        if (!isset($resetPasswordQuery)) {
            return redirect(route('home'));
        }

        if ($request->isMethod('post')) {
            $data = $request->validate([
                'password' => 'string|required'
            ]);

            $resetPasswordQuery->user->forceFill(['password' => Hash::make($data['password'])])->save();
            $resetPasswordQuery->update(['active' => 0]);

            return redirect(route('home'))->with('flash', [
                'error' => false,
                'message' => 'Action effectuée avec succès'
            ]);
        }

        return view('users.reset_password');
    }
}
