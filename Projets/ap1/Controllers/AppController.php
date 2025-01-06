<?php
namespace App\Controllers;

abstract class AppController
{
    protected static function getUser()
    {
        return unserialize($_SESSION["user"]);
    }
    protected static function render($route, $variables = []): void
    {
        if (!empty($_SESSION["user"])) {
            $user = unserialize($_SESSION["user"]);
        }

        extract($variables);

        require VIEW_PATH . DS . $route . ".php";
        require_once VIEW_PATH . DS . "layouts/front.php";
    }
}
