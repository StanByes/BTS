<?php

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $name = $_POST["name"];

    $gender = $_POST["gender"];
    $genderName = $gender == "girl" ? "Fille" : ($gender == "boy" ? "Garçon" : "Non précisé");

    $mail = $_POST["mail"];
    $object = $_POST["object"];
    $content = $_POST["content"];

    $important = isset($_POST["important"]) ? "IMPORTANT" : "";
    $urgent = isset($_POST["urgent"]) ? "URGENT" : "";

    $mailCategory = $important;
    if ($urgent != "")
        $mailCategory .= ($important != "" ? " & " : "") . $urgent;

    if ($mailCategory != "")
        $mailCategory .= " : ";

    echo "OBJECT : $mailCategory $object";
    echo "<br>";
    echo "$genderName $name vous à envoyer un email en utilisant votre formulaire de contact.<br>$content";
    mail($mail, "OBJECT : $mailCategory $object", "$genderName $name vous à envoyer un email en utilisant votre formulaire de contact.<br>$content");
}

?>

<html>
    <head>
        <title>Bastien Riot</title>
        <meta charset="utf-8">

        <link rel="stylesheet" href="assets/css/style.css">
    </head>
    <body>
        <h1 align="center">Bienvenue sur ma page</h1>
        <hr>

        <div class="img">
            <img src="assets/images/me.jpg" alt="my_image" width="175px" height="200px">
        </div>
        <p>Je suis Bastien Riot</p>

        <div align="center">
            <table border="1">
                <thead>
                    <tr>
                        <td colspan="2" align="center"><b><u>Contactez-moi</u></b></td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>tel</td>
                        <td>01.23.45.67.89</td>
                    </tr>
                    <tr>
                        <td>email</td>
                        <td>bastienriot2@gmail.com</td>
                    </tr>
                </tbody>
            </table>

            <p>Vous pouvez aussi me contacter en complétant ce formulaire :</p>

            <div class="contact">
                <form method="post">
                    <div class="form-group">
                        <label for="name">Votre nom :</label>
                        <input id="name" name="name" type="text">
                    </div>

                    <div class="form-group">
                        <p>Votre genre : </p>

                        <label for="girl">Fille</label>
                        <input id="girl" name="gender" type="radio" value="girl">

                        <label for="boy">Garçon</label>
                        <input id="boy" name="gender" type="radio" value="boy">

                        <label for="unknown">Ne souhaite pas répondre</label>
                        <input id="unknown" name="gender" type="radio" value="unknown">
                    </div>

                    <div class="form-group">
                        <label for="mail">Votre email :</label>
                        <input id="mail" name="mail" type="mail">
                    </div>

                    <div class="form-group">
                        <label for="object">Objet de l'email :</label>
                        <input id="object" name="object" type="text">
                    </div>

                    <div class="form-group">
                        <label for="content">Votre message :</label>
                        <textarea id="content" name="content" cols="40" rows="10"></textarea>
                    </div>

                    <div class="form-group">
                        <label for="important">Important</label>
                        <input id="important" name="important" type="checkbox">

                        <label for="urgent">Urgent</label>
                        <input id="urgent" name="urgent" type="checkbox">
                    </div>

                    <button type="submit">Envoyer</button>
                </form>
            </div>
        </div>

        <a href="https://github.com/StanByes/BTS" target="_blank">Lien vers mon dossier des projets BTS</a>
        <a href="assets/cv.pdf" target="_blank">Télécharger mon CV en PDF</a>
    </body>
</html>