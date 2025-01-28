<?php
include "../_conf.php";

if ($connection = mysqli_connect($dbHost, $dbUser, $dbPass, $dbName)) {
    if (empty($_POST['email']) || empty($_POST['birthday'])) {
        echo "Merci de remplir tous les champs ! <br> <a href='./oubli.php'>Retour à la page d'oubli de mot de passe</a>";
        return;
    }

    $mail = $_POST['email'];
    $birthday = $_POST['birthday'];

    $query = $connection->prepare("SELECT `password` FROM `ADHERENT` WHERE `email` = ? AND `dateNaissance` = ?");

    $params = [$mail, $birthday];
    $query->bind_param('ss', ...$params);

    if ($query->execute()) {
        if ($result = $query->get_result()) {
            $data = $result->fetch_assoc();
            if (!isset($data)) {
                echo "Compte introuvable ! <br> <a href='./oubli.php'>Retour à la page d'oubli de mot de passe</a>";
                return;
            }
?>

<!DOCTYPE html>
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

        <div class="card mt-5 m-auto w-25 h-50 shadow p-3 mb-5 bg-body rounded">
            <div class="card-body">
                <span>Mot de passe oublié</span>

                <p>Votre mot de passe est <strong><?= $data['password'] ?></strong></p>
            </div>
        </div>

        <div class="d-flex flex-column align-items-center justify-content-center">
            <p>Pas encore inscrit ? <a href="./inscription.php">Inscrivez-vous</a></p>
            <br>
            <ul class="list-group list-group-horizontal">
                <li class="list-group-item">Stackfindover</li>
                <li class="list-group-item">Contact</li>
                <li class="list-group-item">Privacy & terms</li>
            </ul>
        </div>
    </body>
</html>

<?php
        }
    }
}
?>
