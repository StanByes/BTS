<?php
include "../_conf.php";

$values = array();
foreach (array('name', 'firstname', 'email', 'login', 'password', 'verif_password', 'birthday', 'gender', 'bac', 'tel') as $v) {
    if (empty($_POST[$v]) && $v !== 'tel' && $v !== 'bac') {
        echo "Le champ $v est vide ! Merci de remplir tous les champs. <br> <a href='./inscription.php'>Revenir à la page d'inscription</a>";
        return;
    }

    if ($v == 'gender')
        $_POST['gender'] = $_POST['gender'] == 'man' ? 0 : 1;

    if ($v !== 'verif_password')
        $values[] = $v == 'password'
            ? password_hash($_POST[$v], PASSWORD_BCRYPT)
            : ($v == 'tel' || $v == 'bac' && empty($_POST[$v])
                ? null
                : $_POST[$v]);
}

if ($_POST['password'] !== $_POST['verif_password']) {
    echo "Les mots de passes doivent correspondre ! <br> <a href='./inscription.php'>Revenir à la page d'inscription</a>";
    return;
}

if ($connection = mysqli_connect($dbHost, $dbUser, $dbPass, $dbName)) {
    echo "Connecté à la base de données !<br>";

    $query = $connection->prepare("INSERT INTO `ADHERENT`(`nom`, `prenom`, `email`, `login`, `password`, `dateNaissance`, `Sexe`, `Annee_BAC`, `tel`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
    $query->bind_param("ssssssiis", ...$values);
    
    if ($query->execute()) {
        echo "Inscription effectuée avec succès ! <br> <a href='./index.php'>Page de connexion</a>";
    } else {
        echo "Erreur d'insertion dans la base de données : " . $query->error;
    }
} else {
    echo "Erreur de connexion à la base de données";
}
