<?php
session_start();

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    if (strlen($_POST["password"]) < 10) {
        echo "<span>Le mot de passe est trop court. Veuillez réessayer sur <a href='./index.php'>cette page</a></span>";
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
        <link rel="stylesheet" href="./assets/style.css">
    </head>

    <body>
        <div class="confirmation">
            <h3>Bonjour <?= $genderName . " " . $_POST["name"] ?></h3>
            <p>Coût inscription : <?= $price ?></p>
            <a href="confirmer.php"><button>Confirmer</button></a>
        </div>
    </body>
</html>