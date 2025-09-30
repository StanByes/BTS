<?php
$title = "Connexion";
$card_title = $title;

ob_start();
?>

<form method="post">
    <div class="mb-3 form-group">
        <label for="login" class="form-label">Login / Email</label>
        <input id="login" class="form-control" type="text" name="login" required="required">
    </div>

    <div class="form-group mb-3">
        <label for="password" class="form-label">Mot de passe</label>
        <input id="password" class="form-control" type="password" name="password" required="required">
    </div>

    <div class="d-flex justify-content-around">
        <div class="mb-2">
            <a class="text-decoration-none" href="./?action=reset_password_query">Mot de passe oublié ?</a>
        </div>

        <div class="mb-2">
            <a class="text-decoration-none" href="./?action=sign">Pas encore inscrit ?</a>
        </div>
    </div>

    <div class="d-flex justify-content-center mt-4">
        <button class="btn btn-primary w-75">Valider</button>
    </div>
</form>

<?php
$content = ob_get_clean();
?>
