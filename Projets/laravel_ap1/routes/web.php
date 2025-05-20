<?php

use App\Http\Controllers\Reports\ReportController;
use App\Http\Controllers\Users\UserController;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\Pages\PageController;

Route::get('/', [PageController::class, 'home'])->middleware('auth')->name('home');
Route::any('/login', [UserController::class, 'login'])->name('login');
Route::get('/logout', [UserController::class, 'logout']);
Route::resource('reports', ReportController::class)->only(['store']);
Route::resource('users', UserController::class)->only(['update']);
Route::any('/reset_password_query', [UserController::class, 'resetPasswordQuery']);
Route::any('/reset_password', [UserController::class, 'resetPassword']);
