<?php

namespace App\Http\Controllers\Reports;

use App\Http\Controllers\Controller;
use Illuminate\Foundation\Application;
use Illuminate\Http\RedirectResponse;
use Illuminate\Http\Request;
use Illuminate\Routing\Redirector;
use Illuminate\Support\Facades\Route;

class ReportController extends Controller
{
    public function store(Request $request): Application|Redirector|RedirectResponse
    {
        $data = $request->validate([
            'title' => 'required|string',
            'content' => 'required|string',
            'date' => 'required|date'
        ]);

        $this->getUser()->reports()->create($data);
        return redirect(route('home'))->with('flash', [
            'error' => false,
            'message' => 'Compte rendu publié avec succès'
        ]);
    }
}
