<?php
$title = "Connexion";
$card_title = $title;

ob_start();
?>

<form method="post">
    <div class="mb-3 form-group">
        <label for="login" class="form-label">Login / Email</label>
        <input id="login" class="form-control" type="text" name="login">
    </div>

    <div class="form-group mb-3">
        <label for="password" class="form-label">Mot de passe</label>
        <input id="password" class="form-control" type="password" name="password">
    </div>

    <div class="d-flex justify-content-center mt-4">
        <button class="btn btn-primary w-75">Valider</button>
    </div>
</form>

<?php
$content = ob_get_clean();
?>
