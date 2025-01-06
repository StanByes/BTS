<?php
$title = "Accueil";
$card_title = "Espace " . $user->getRole()->getDisplayName();
$card_size = "w-75";

ob_start();
include VIEW_PATH . DS . "home" . DS . $user->getRole()->getName() . ".php";
$content = ob_get_clean();
