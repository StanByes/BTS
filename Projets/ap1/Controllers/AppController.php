<?php
namespace App\Controllers;

use App\Entities\User;
use App\Models\InternshipModel;

abstract class AppController
{
    protected static function getUser(): User
    {
        return unserialize($_SESSION["user"]);
    }

    protected static function sendMail($to, $subject, $content)
    {
        $report = "reports@internship.fr";
        mail($to, $subject, "$content", "From: $report");
    }

    protected static function render($route, $variables = []): void
    {
        if (!empty($_SESSION["user"])) {
            $user = unserialize($_SESSION["user"]);
            $internship = InternshipModel::getInternshipByUser($user);
        }

        extract($variables);

        require VIEW_PATH . DS . $route . ".php";
        require_once VIEW_PATH . DS . "layouts/front.php";
    }

    protected static function flash($error, $message): void
    {
        $_SESSION["flash"] = ["error" => $error, "message" => $message];
    }

    public static function testPassword()
    {
        self::render("debug/test_password");
    }
}
