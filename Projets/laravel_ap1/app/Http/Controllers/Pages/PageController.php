<?php

namespace App\Http\Controllers\Pages;
use App\Enum\RoleEnum;
use App\Http\Controllers\Controller;
use App\Models\Report;
use App\Models\User;
use Illuminate\Contracts\View\Factory;
use Illuminate\Contracts\View\View;
use Illuminate\Foundation\Application;
use Illuminate\Http\RedirectResponse;
use Illuminate\Http\Request;
use Illuminate\Routing\Redirector;

class PageController extends Controller
{
    public function home(Request $request): Factory|View|Application|Redirector|RedirectResponse
    {
        $user = $this->getUser();
        $data = [
            'interns' => array(),
            'supervisors' => array(),
            'searchedUser' => null,
            'totalReports' => 0
        ];

        if ($user->role->name == "supervisor") {
            if (!empty($request->query('user'))) {
                $searchedUser = User::find($request->query('user'));
                if (!isset($searchedUser)) {
                    return redirect(route('home'))->with('flash', [
                        'error' => true,
                        'message' => 'Utilisateur introuvable'
                    ]);
                }

                if ($searchedUser->role->name != RoleEnum::INTERN->value) {
                    return redirect(route('home'))->with('flash', [
                        'error' => true,
                        'message' => 'L\'utilisateur n\'est pas un élève'
                    ]);
                }

                if ($searchedUser->internship->supervisor->id != $user->id) {
                    return redirect(route('home'))->with('flash', [
                        'error' => true,
                        'message' => 'L\'utilisateur n\'est pas votre élève'
                    ]);
                }

                $data['specificUser'] = $searchedUser;
            }
        } elseif ($user->role->name == "gestion") {
            $data['interns'] = User::getByRole(RoleEnum::INTERN)->get();
            $data['supervisors'] = User::getByRole(RoleEnum::SUPERVISOR)->get();
            $data['totalReports'] = Report::count();
        }

        return view('pages.home', $data);
    }
}
