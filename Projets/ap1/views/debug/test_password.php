<?php

use App\Models\UserModel;

$title = "DEBUG - PASSWORD";
$card_title = $title;
ob_start();

echo "Jeu de test MDP : <br>";
echo "Mot de passe <strong>test (-10 caractères | seulement des minuscules)</strong> : "
. (UserModel::checkPasswordSecurity("test") ? "Valide" : "Invalide");
echo "<br>";
echo "<hr>";
echo "Mot de passe <strong>testtestte (10 caractères pile | seulement des minuscules)</strong> : "
. (UserModel::checkPasswordSecurity("testtestte") ? "Valide" : "Invalide");
echo "<br>";
echo "<hr>";
echo "Mot de passe <strong>testtesttest (+10 caractères | seulement des minuscules)</strong> : "
. (UserModel::checkPasswordSecurity("testtesttest") ? "Valide" : "Invalide");
echo "<br>";
echo "<hr>";
echo "Mot de passe <strong>TESTTESTTEST (+10 caractères | seulement des majuscules)</strong> : "
. (UserModel::checkPasswordSecurity("TESTTESTTEST") ? "Valide" : "Invalide");
echo "<br>";
echo "<hr>";
echo "Mot de passe <strong>0123456789102 (+10 caractères | seulement des chiffres)</strong> : "
. (UserModel::checkPasswordSecurity("0123456789102") ? "Valide" : "Invalide");
echo "<br>";
echo "<hr>";
echo "Mot de passe <strong>------------ (+10 caractères | seulement des symboles)</strong> : "
. (UserModel::checkPasswordSecurity("------------") ? "Valide" : "Invalide");
echo "<br>";
echo "<hr>";
echo "Mot de passe <strong>test123456789 (+10 caractères | minuscules & chiffres)</strong> : "
. (UserModel::checkPasswordSecurity("test123456789") ? "Valide" : "Invalide");
echo "<br>";
echo "<hr>";
echo "Mot de passe <strong>TEST123456789 (+10 caractères | majuscules & chiffres)</strong> : "
. (UserModel::checkPasswordSecurity("TEST123456789") ? "Valide" : "Invalide");
echo "<br>";
echo "<hr>";
echo "Mot de passe <strong>----123456789 (+10 caractères | chiffres & symboles)</strong> : "
. (UserModel::checkPasswordSecurity("----123456789") ? "Valide" : "Invalide");
echo "<br>";
echo "<hr>";
echo "Mot de passe <strong>testtesttesttest--- (+10 caractères | minuscules & symboles)</strong> : "
. (UserModel::checkPasswordSecurity("testtesttesttest---") ? "Valide" : "Invalide");
echo "<br>";
echo "<hr>";
echo "Mot de passe <strong>TESTTESTTEST--- (+10 caractères | majuscules & symboles)</strong> : "
. (UserModel::checkPasswordSecurity("TESTTESTTEST---") ? "Valide" : "Invalide");
echo "<br>";
echo "<hr>";
echo "Mot de passe <strong>Testtesttest (+10 caractères | minuscules & majuscule)</strong> : "
. (UserModel::checkPasswordSecurity("Testtesttest") ? "Valide" : "Invalide");
echo "<br>";
echo "<hr>";
echo "Mot de passe <strong>Test123456789 (+10 caractères | minuscules & majuscule & chiffres)</strong> : "
. (UserModel::checkPasswordSecurity("Test123456789") ? "Valide" : "Invalide");
echo "<br>";
echo "<hr>";
echo "Mot de passe <strong>Testtesttest--s (+10 caractères | minuscules & majuscule & symboles)</strong> : "
. (UserModel::checkPasswordSecurity("Testtesttest--s") ? "Valide" : "Invalide");
echo "<br>";
echo "<hr>";
echo "Mot de passe <strong>testtesttest1524((( (+10 caractères | minuscules & chiffres & symboles)</strong> : "
. (UserModel::checkPasswordSecurity("testtesttest1524(((") ? "Valide" : "Invalide");
echo "<br>";
echo "<hr>";
echo "Mot de passe <strong>TESTTESTTEST1524((( (+10 caractères | majuscules & chiffres & symboles)</strong> : "
. (UserModel::checkPasswordSecurity("TESTTESTTEST1524(((") ? "Valide" : "Invalide");
echo "<br>";
echo "<hr>";
echo "Mot de passe <strong>Testt1esttest--s (+10 caractères | minuscules & majuscule & chiffre & symboles)
</strong> : "
. (UserModel::checkPasswordSecurity("Testt1esttest--s") ? "Valide" : "Invalide");

$content = ob_get_clean();
