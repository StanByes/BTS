<?php
include "../_conf.php";

$values = array();
foreach (array('name', 'firstname', 'email', 'login', 'password', 'verif_password', 'birthday', 'gender', 'bac') as $v) {
    if (empty($_POST[$v])) {
        echo "Le champ $v est vide ! Merci de remplir tous les champs.";
        return;
    }

    if ($v == 'gender')
        $_POST['gender'] = $_POST['gender'] == 'man' ? 0 : 1;

    if ($v !== 'verif_password')
        array_push($values, $v == 'password' ? password_hash($_POST[$v], PASSWORD_BCRYPT) : $_POST[$v]);
}

if ($_POST['password'] !== $_POST['verif_password']) {
    echo "Les mots de passes doivent correspondre !";
    return;
}

if ($connection = mysqli_connect($dbHost, $dbUser, $dbPass, $dbName)) {
    $get_account = $connection->prepare("SELECT * FROM `ADHERENT` WHERE `login` = ?");
    $get_account->bind_param("s",$_POST['old_login']);
    
    if ($get_account->execute()) {
        if ($result = $get_account->get_result()) {
            $data = $result->fetch_assoc();
            if (!isset($data)) {
                echo "Login inconnu !";
                return;
            }

            if (!password_verify($_POST['old_password'], $data['password'])) {
                echo "Mauvais mot de passe !";
                return;
            }

            array_push($values, $_POST['old_login']);

            $update_account = $connection->prepare("UPDATE `ADHERENT` SET `nom` = ?, `prenom` = ?, `email` = ?, `login` = ?, `password` = ?, `dateNaissance` = ?, `Sexe` = ?, `Annee_BAC` = ? WHERE `login` = ?");
            $update_account->bind_param("ssssssiis", ...$values);
    
            if ($update_account->execute()) {
                echo "Modification effectuée avec succès !";
            } else {
                echo "Erreur de mise à jour de la base de données : " . $query->error;
            }
        }
    }
} else {
    echo "Erreur de connexion à la base de données";
}