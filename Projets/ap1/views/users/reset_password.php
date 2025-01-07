<?php
$title = "Réinitialisation de mot de passe";
$card_title = $title;

ob_start();
?>

<form method="post">
    <div class="form-group mb-3">
        <label class="form-label" for="password">Nouveau mot de passe :</label>
        <input id="password" class="form-control" name="password" type="password">
    </div>

    <div class="d-flex justify-content-center mt-4">
        <button class="btn btn-primary w-75">Valider</button>
    </div>
</form>

<?php
$content = ob_get_clean();
?>
