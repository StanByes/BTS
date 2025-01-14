<?php
namespace App\Controllers;

use App\Models\InternshipModel;
use App\Models\ResetPasswordQueryModel;
use App\Models\UserModel;
use http\Exception\RuntimeException;
use JetBrains\PhpStorm\NoReturn;
use Random\RandomException;

class UserController extends AppController
{
    public static function login(): void
    {
        if (!empty($_SESSION) && !empty($_SESSION["user"])) {
            header("Location: ./");
            die();
        }

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

    #[NoReturn] public static function logout(): void
    {
        session_destroy();
        header("Location: ./");
        die();
    }

    public static function resetPasswordQuery(): void
    {
        if ($_SERVER["REQUEST_METHOD"] == "POST") {
            $mail = strip_tags($_POST['mail']);
            if (empty($mail)) {
                self::flash(true, "Veuillez remplir tous les champs");
                AppController::render("users/reset_password_query");
                return;
            }

            $user = UserModel::getUser($mail, true);
            if ($user == null) {
                self::flash(true, "Adresse mail inconnue");
                AppController::render("users/reset_password_query");
                return;
            }

            try {
                $code = bin2hex(random_bytes(20 / 2));
            } catch (RandomException $exception) {
                throw new RuntimeException($exception);
            }

            ResetPasswordQueryModel::createQuery($user, $code);

            if (isset($_SERVER['HTTPS']) && $_SERVER['HTTPS'] === 'on') {
                $url = "https://";
            } else {
                $url = "http://";
            }

            $url .= $_SERVER['HTTP_HOST'] . explode("?", $_SERVER['REQUEST_URI'])[0];
            $url .= "?action=reset_password&code=" . $code;
            $content = "Vous avez demandé à réinitialiser votre mot de passe sur notre plateforme !
Voici un lien de réinitialisation unique, valable 1 heure : " . $url;
            self::sendMail($mail, "Réinitialisation de mot de passe", $content);

            self::flash(false, "Mail envoyé avec succès");

            header("Location: ./");
            die();
        }

        AppController::render("users/reset_password_query");
    }

    public static function resetPassword(): void
    {
        if (!isset($_GET["code"])) {
            header("Location: ./");
            die();
        }

        $code = $_GET["code"];
        $resetPasswordQuery = ResetPasswordQueryModel::getQueryByCode($code);
        if (!isset($resetPasswordQuery)) {
            header("Location: ./");
            die();
        }

        if ($_SERVER["REQUEST_METHOD"] == "POST") {
            $password = strip_tags($_POST['password']);
            if (empty($password)) {
                self::flash(true, "Veuillez remplir tous les champs");
                AppController::render("users/reset_password", compact("resetPasswordQuery"));
                return;
            }

            UserModel::changePassword($resetPasswordQuery->getUser(), password_hash($password, PASSWORD_BCRYPT));
            ResetPasswordQueryModel::unavailableQuery($resetPasswordQuery);

            self::flash(false, "Action effectuée avec succès");

            header("Location: ./");
            die();
        }

        AppController::render("users/reset_password", compact("resetPasswordQuery"));
    }

    public static function update(): void
    {
        $surname = strip_tags($_POST['surname']);
        $firstname = strip_tags($_POST['firstname']);
        $mail = strip_tags($_POST['mail']);

        $internshipStartAt = strip_tags($_POST["start_at"]);
        $internshipEndAt = strip_tags($_POST["end_at"]);

        if (empty($surname) || empty($firstname) || empty($mail)
            || empty($internshipStartAt) || empty($internshipEndAt)) {
            self::flash(true, "Veuillez remplir tous les champs obligatoires");
            header("Location: ./");
            die();
        }

        $internshipDayStartAt = $_POST["day_start_at"] ?? null;
        $internshipDayEndAt = $_POST["day_end_at"] ?? null;

        UserModel::updateUser(self::getUser(), $surname, $firstname, $mail);
        InternshipModel::updateInternship(
            self::getUser(),
            $internshipStartAt,
            $internshipEndAt,
            $internshipDayStartAt,
            $internshipDayEndAt
        );

        self::flash(false, "Profil mis à jour avec succès");
        header("Location: ./");
        die();
    }
}
