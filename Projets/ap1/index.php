<?php
namespace App;

require_once "./autoload.php";
require_once "./global.php";

use App\Controllers\HomeController;
use App\Controllers\ReportController;
use App\Controllers\UserController;

session_start();
$action = $_GET['action'] ?? '';

if (empty($_SESSION)) {
    if ($action !== 'login') {
        header("Location: ?action=login");
        die();
    }
}

switch ($action) {
    case "login":
        UserController::login();
        break;
    case "logout":
        UserController::logout();
        break;
    case "create_report":
        ReportController::create();
        break;
    default:
        HomeController::home();
        break;
}
