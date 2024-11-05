<?php

$headers  = "From: no-reply-microsoft-help@gmail.com\r\n";
$headers .= "Reply-To: no-reply-microsoft-help@gmail.com\r\n";
$headers .= "MIME-Version: 1.0\r\n";
$headers .= "Content-Type: text/html; charset=UTF-8\r\n";

$message = "<h1>Activité de connexion inhabituelle</h1><p>Nous avons détecté une activité suspecte sur votre compte test@gmail.com</p><p>Détection : Russie/Moscou -> 185.19.26.54<p><strong>Nous vous connseillon de vou connecté rapidement pour ne pas perdre votre compte</strong><br><a href='http://localhost/formation'><button>Changer son mot de passe</button></a>";
mail("bastienriot2@gmail.com", "Alerte de sécurité Microsoft", $message, $headers);