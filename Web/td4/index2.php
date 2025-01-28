<?php
include "../_conf.php";

if ($connection = mysqli_connect($dbHost, $dbUser, $dbPass, $dbName)) {
    if (empty($_POST['login']) || empty($_POST['password']) || empty($_POST['code'])) {
        echo "Merci de remplir tous les champs ! <br> <a href='./index.php'>Retour à la page de connexion</a>";
        return;
    }

    $login = $_POST['login'];
    $password = $_POST['password'];
    $code = $_POST['code'];

    if (strlen($code) != 5) {
        echo "Le code doit être composé de 5 chiffres ! <br> <a href='./index.php'>Retour à la page de connexion</a>";
        return;
    }

    $query = $connection->prepare("SELECT `id`, `nom`, `prenom`, `email`, `login`, `password`, `dateNaissance`, `Sexe`, `Annee_BAC`, `tel` FROM `ADHERENT` WHERE `login` = ?");
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

            $codeQuery = $connection->prepare("SELECT `code` FROM `CODE`");

            $goodCode = false;
            if ($codeQuery->execute()) {
                if ($result = $codeQuery->get_result()) {
                    $codeData = $result->fetch_assoc();
                    if (!isset($data)) {
                        echo "Erreur Interne ! Aucun code défini dans la base de données ! <br> <a href='./index.php'>Retour à la page de connexion</a>";
                        return;
                    }

                    $codeInBase = $codeData["code"];
                    $goodCode = $codeInBase == $code;
                }
            } else {
                echo "Erreur de récupération de données : " . $query->error;
            }

            if (!$goodCode) {
                echo "Code Invalide ! <br> <a href='./index.php'>Retour à la page de connexion</a>";
                return;
            }

            ?>
            <html>
                <head>
                    <meta charset='utf-8'>
                    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
                    <title>Stackfindover</title>
                    <meta name='viewport' content='width=device-width, initial-scale=1'>
                    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
                </head>
                <body>
                    <h1 class="text-center">Stackfindover</h1>

                    <div class="card mt-5 m-auto w-25 shadow p-3 mb-5 bg-body rounded">
                        <div class="card-body">
                            <span>Mettre à jour vos informations</span>

                            <form action="./update.php" method="POST">
                                <input type="hidden" name="old_login" value="<?= $data['login'] ?>">

                                <div class="form-group mt-4">
                                    <label for="login" class="form-label">Login</label>
                                    <input type="text" id="login" name="login" class="form-control" value="<?= $data['login'] ?>">
                                </div>

                                <div class="form-group mt-4">
                                    <label for="old_password" class="form-label">Ancien mot de passe</label>
                                    <input type="password" id="old_password" name="old_password" class="form-control">
                                </div>

                                <div class="form-group mt-4">
                                    <label for="password" class="form-label">Nouveau mot de passe</label>
                                    <input type="password" id="password" name="password" class="form-control">
                                </div>

                                <div class="form-group mt-4">
                                    <label for="verif_password" class="form-label">Répéter le mot de passe</label>
                                    <input type="password" id="verif_password" name="verif_password" class="form-control">
                                </div>

                                <div class="form-group mt-4">
                                    <label for="name" class="form-label">Nom</label>
                                    <input type="text" id="name" name="name" class="form-control" value="<?= $data['nom'] ?>">
                                </div>

                                <div class="form-group mt-4">
                                    <label for="firstname" class="form-label">Prénom</label>
                                    <input type="text" id="firstname" name="firstname" class="form-control" value="<?= $data['prenom'] ?>">
                                </div>

                                <div class="form-group mt-4">
                                    <label for="email" class="form-label">Email</label>
                                    <input type="email" id="email" name="email" class="form-control" value="<?= $data['email'] ?>">
                                </div>

                                <div class="form-group mt-4">
                                    <label for="date" class="form-label">Date de naissance</label>
                                    <input type="date" id="birthday" name="birthday" class="form-control" value="<?= $data['dateNaissance'] ?>">
                                </div>

                                <p class="form-label mt-4">Genre</p>
                                <div class="form-check">
                                    <label for="gender_man" class="form-label">Homme</label>
                                    <input type="radio" id="gender_man" name="gender" class="form-check-input" value="man" <?= $data['Sexe'] == 0 ? "checked" : "" ?>>
                                </div>
                                <div class="form-check">
                                    <label for="woman_man" class="form-label">Femme</label>
                                    <input type="radio" id="woman_man" name="gender" class="form-check-input" value="woman" <?= $data['Sexe'] == 1 ? "checked" : "" ?>>
                                </div>

                                <div class="form-group mt-4">
                                    <label for="bac" class="form-label">Année du bac</label>
                                    <select id="password" name="bac" class="form-control">
                                        <?php
                                            for ($i = date('Y'); $i >= 1970; $i--) { ?>
                                                <option value="<?= $i ?>" <?= $data["Annee_BAC"] == $i ? "selected" : "" ?>><?= $i ?></option>
                                            <?php } ?>
                                    </select>
                                </div>

                                <div class="mt-4">
                                    <p><strong>Numéro de téléphone : </strong> <?= isset($data["tel"]) ? $data["tel"] : "Non renseigné" ?></p>
                                </div>

                                <div class="d-flex justify-content-center mt-5">
                                    <button class="btn btn-success w-100">S'incrire</button>
                                </div>
                            </form>
                        </div>
                    </div>

                    <div class="d-flex flex-column align-items-center justify-content-center">
                        <ul class="list-group list-group-horizontal">
                            <li class="list-group-item">Stackfindover</li>
                            <li class="list-group-item">Contact</li>
                            <li class="list-group-item">Privacy & terms</li>
                        </ul>
                    </div>
                </body>
            </html>
        <?php }
    } else {
        echo "Erreur de récupération de données : " . $query->error;
    }
} else {
    echo "Erreur de connexion à la base de données";
}
