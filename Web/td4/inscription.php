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
                <span>Inscription</span>

                <form action="./inscription2.php" method="POST">
                    <div class="form-group mt-4">
                        <label for="login" class="form-label">Login</label>
                        <input type="text" id="login" name="login" class="form-control">
                    </div>

                    <div class="form-group mt-4">
                        <label for="password" class="form-label">Mot de passe</label>
                        <input type="password" id="password" name="password" class="form-control">
                    </div>

                    <div class="form-group mt-4">
                        <label for="verif_password" class="form-label">Répéter le mot de passe</label>
                        <input type="password" id="verif_password" name="verif_password" class="form-control">
                    </div>

                    <div class="form-group mt-4">
                        <label for="name" class="form-label">Nom</label>
                        <input type="text" id="name" name="name" class="form-control">
                    </div>

                    <div class="form-group mt-4">
                        <label for="firstname" class="form-label">Prénom</label>
                        <input type="text" id="firstname" name="firstname" class="form-control">
                    </div>

                    <div class="form-group mt-4">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" id="email" name="email" class="form-control">
                    </div>

                    <div class="form-group mt-4">
                        <label for="birthday" class="form-label">Date de naissance</label>
                        <input type="date" id="birthday" name="birthday" class="form-control">
                    </div>

                    <p class="form-label mt-4">Genre</p>
                    <div class="form-check">
                        <label for="gender_man" class="form-label">Homme</label>
                        <input type="radio" id="gender_man" name="gender" class="form-check-input" value="man">
                    </div>
                    <div class="form-check">
                        <label for="woman_man" class="form-label">Femme</label>
                        <input type="radio" id="woman_man" name="gender" class="form-check-input" value="woman">
                    </div>

                    <div class="form-group mt-4">
                        <label for="bac" class="form-label">Année du bac</label>
                        <select id="password" name="bac" class="form-control">
                            <?php
                                for ($i = date('Y'); $i >= 1970; $i--) { ?>
                                    <option value="<?= $i ?>"><?= $i ?></option>
                                <?php } ?>
                        </select>
                    </div>

                    <div class="form-group mt-4">
                        <label for="tel" class="form-label">Numéro de téléphone (optionnel)</label>
                        <input type="tel" id="tel" name="tel" class="form-control">
                    </div>

                    <div class="d-flex justify-content-center mt-5">
                        <button class="btn btn-success w-100">S'incrire</button>
                    </div>
                </form>
            </div>
        </div>

        <div class="d-flex flex-column align-items-center justify-content-center">
            <p>Déjà inscrit ? <a href="./index.php">Connectez-vous</a></p>
            <br>
            <ul class="list-group list-group-horizontal">
                <li class="list-group-item">Stackfindover</li>
                <li class="list-group-item">Contact</li>
                <li class="list-group-item">Privacy & terms</li>
            </ul>
        </div>
    </body>
</html>
