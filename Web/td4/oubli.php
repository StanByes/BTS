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

                <form action="./oubli2.php" method="POST">
                    <div class="form-group mt-4">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" id="email" name="email" class="form-control">
                    </div>

                    <div class="form-group mt-4">
                        <label for="birthday" class="form-label">Date de naissance</label>
                        <input type="date" id="birthday" name="birthday" class="form-control">
                    </div>

                    <div class="d-flex justify-content-center mt-5">
                        <button class="btn btn-success w-100">Envoyer</button>
                    </div>
                </form>
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
