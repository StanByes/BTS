<?php
namespace App;

require_once "./autoload.php";
require_once "./global.php";
require_once "./function.php";

use App\Controllers\AppController;
use App\Controllers\HomeController;
use App\Controllers\ReportController;
use App\Controllers\UserController;
use App\Models\ResetPasswordQueryModel;

ResetPasswordQueryModel::cleanOldQueries();

session_start();
$action = $_GET['action'] ?? '';

if (empty($_SESSION["user"])) {
    if (!str_starts_with($action, "test")
        && $action !== 'login' && $action !== 'reset_password_query' && $action !== 'reset_password'
        && $action !== 'sign') {
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
    case "reset_password_query":
        UserController::resetPasswordQuery();
        break;
    case "reset_password":
        UserController::resetPassword();
        break;
    case "create_report":
        ReportController::create();
        break;
    case "edit_user":
        UserController::update();
        break;
    case "sign":
        UserController::sign();
        break;
    case "test_password":
        AppController::testPassword();
        break;
    case "edit_report":
        ReportController::edit();
        break;
    case "update_report":
        ReportController::update();
        break;
    default:
        HomeController::home();
        break;
}
