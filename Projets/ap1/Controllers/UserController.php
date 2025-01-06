<?php
namespace App\Controllers;

use App\Models\UserModel;

class UserController extends AppController
{
    public static function login(): void
    {
        if ($_SERVER['REQUEST_METHOD'] == 'POST') {
            $login = strip_tags($_POST['login']);
            $password = strip_tags($_POST['password']);

            if (empty($login) || empty($password)) {
                self::flash(true, "Veuillez remplir tous les champs");
                header("Location: ./?action=login");
                die();
            }

            $use_mail = filter_var($login, FILTER_VALIDATE_EMAIL);

            $account = UserModel::getUser($login, $use_mail);
            if (!isset($account)) {
                self::flash(true, "Login introuvable");
                header("Location: ./?action=login");
                die();
            }

            if (!UserModel::verifyPassword($password, $account->getPassword())) {
                self::flash(true, "Couple login/mot de passe incorrect");
                header("Location: ./?action=login");
                die();
            }

            $_SESSION['user'] = serialize($account);
            self::flash(false, "Connecté avec succès");

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
