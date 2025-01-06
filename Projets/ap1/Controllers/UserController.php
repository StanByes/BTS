<?php
namespace App\Controllers;

use App\Models\UserModel;
use JetBrains\PhpStorm\NoReturn;

class UserController extends AppController
{
    public static function login(): void
    {
        if ($_SERVER['REQUEST_METHOD'] == 'POST') {
            $login = strip_tags($_POST['login']);
            $password = strip_tags($_POST['password']);

            if (empty($login) || empty($password)) {
                // TODO : Send flash message
                return;
            }

            $use_mail = filter_var($login, FILTER_VALIDATE_EMAIL);

            $account = UserModel::getUser($login, $use_mail);
            if (!isset($account)) {
                // TODO : Send flash message
                return;
            }

            if (!UserModel::verifyPassword($password, $account->getPassword())) {
                // TODO : Send flash message
                return;
            }

            $_SESSION['user'] = serialize($account);

            header("Location: ./");
            die();
        }

        AppController::render("users/login");
    }

    public static function logout(): void
    {
        session_destroy();
        header("Location: ./");
        die();
    }
}
