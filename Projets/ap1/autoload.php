<?php
$autoload = function ($class_path) {
    $class_path = str_replace("App\\", "", $class_path);
    $class_path = str_replace('\\', DIRECTORY_SEPARATOR, $class_path);

    $full_path = ROOT_PATH . DS . $class_path . ".php";

    if (is_file($full_path)) {
        require_once $full_path;
        return true;
    }

    return false;
};

spl_autoload_register($autoload);
