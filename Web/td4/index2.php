<?php
include "../_conf.php";

if ($connection = mysqli_connect($dbHost, $dbUser, $dbPass, $dbName)) {
    echo "Connecté à la base de données !<br>";

    if (empty($_POST['login']) || empty($_POST['password'])) {
        echo "Merci de remplir tous les champs ! <br> <a href='./index.php'>Retour à la page de connexion</a>";
        return;
    }

    $login = $_POST['login'];
    $password = $_POST['password'];

    $query = $connection->prepare("SELECT `id`, `nom`, `prenom`, `email`, `login`, `password`, `dateNaissance`, `Sexe`, `Annee_BAC` FROM `ADHERENT` WHERE `login` = ?");
    $query->bind_param('s', $login);

    if ($query->execute()) {
        if ($result = $query->get_result()) {
            $data = $result->fetch_assoc();
            if (!isset($data)) {
                echo "Login inconnu ! <br> <a href='./index.php'>Retour à la page de connexion</a>";
                return;
            }

            $accountPass = $data['password'];
            if (!password_verify($password, $accountPass)) {
                echo "Mauvais couple login/password ! <br> <a href='./index.php'>Retour à la page de connexion</a>";
                return;
            }

            echo "Connecté avec succès !<br>";
            echo "Bienvenue !<br>";
            foreach ($data as $k => $v)
                if ($k !== 'password')
                    echo $k . " -> " . $v . "<br>";
        }
    } else {
        echo "Erreur de récupération de données : " . $query->error;
    }
} else {
    echo "Erreur de connexion à la base de données";
}