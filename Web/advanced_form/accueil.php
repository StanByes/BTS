<?php
session_start();

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    if (strlen($_POST["password"]) < 10) {
        echo "<span>Le mot de passe est trop court. Veuillez réessayer sur <a href='/'>cette page</a></span>";
        return;
    }

    $genderName = $_POST["gender"] == "man" ? "Monsieur" : "Madame";
    switch ($_POST["category"]) {
        case "2": 
            $price = 10;
            break;
        case "3": 
            $price = 50;
            break;
        case "4": 
            $price = "gratuit";
            break;
        default: 
            $price = 20;
            break;
    }

    $_SESSION = $_POST;
}
?>

<html>
    <head>
        <title>Confirmation</title>
    </head>

    <body>
        <p>Bonjour <?= $genderName . " " . $_POST["name"] ?></p>
        <p>Coût inscription : <?= $price ?></p>
        <a href="confirmer.php"><button style="background-color: blue; padding: 1%; color: white;">Confirmer</button></a>
    </body>
</html>