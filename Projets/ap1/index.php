<?php
namespace App;

require_once "./autoload.php";
require_once "./global.php";
require_once "./function.php";

use App\Controllers\HomeController;
use App\Controllers\ReportController;
use App\Controllers\UserController;
use App\Models\ResetPasswordQueryModel;

ResetPasswordQueryModel::cleanOldQueries();

session_start();
$action = $_GET['action'] ?? '';

if (empty($_SESSION["user"])) {
    if ($action !== 'login' && $action !== 'reset_password_query' && $action !== 'reset_password') {
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
    default:
        HomeController::home();
        break;
}
