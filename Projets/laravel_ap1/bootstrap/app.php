<?php

use App\Models\ResetPasswordQuery;
use Illuminate\Console\Scheduling\Schedule;
use Illuminate\Foundation\Application;
use Illuminate\Foundation\Configuration\Exceptions;
use Illuminate\Foundation\Configuration\Middleware;

return Application::configure(basePath: dirname(__DIR__))
    ->withRouting(
        web: __DIR__.'/../routes/web.php',
        health: '/up',
    )
    ->withMiddleware(function (Middleware $middleware) {})
    ->withSchedule(function (Schedule $schedule) {
        $schedule->call(function () {
            ResetPasswordQuery::clearUnavailableQueries();
        })->everyTwoHours();
    })
    ->withExceptions(function (Exceptions $exceptions) {})
    ->create();
