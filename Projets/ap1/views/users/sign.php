<?php
$title = "Inscription";
$card_title = $title;

ob_start();
?>

<form method="post">
    <h4>Informations personnelles</h4>
    <div class="row">
        <div class="mb-3 form-group col-6">
            <label for="surname" class="form-label">Nom</label>
            <input id="surname" class="form-control" type="text" name="surname" required="required">
        </div>

        <div class="mb-3 form-group col-6">
            <label for="firstname" class="form-label">Prénom</label>
            <input id="firstname" class="form-control" type="text" name="firstname" required="required">
        </div>
    </div>

    <div class="row">
        <div class="mb-3 form-group col-6">
            <label for="login" class="form-label">Nom d'utilisateur</label>
            <input id="login" class="form-control" type="text" name="login" required="required">
        </div>

        <div class="mb-3 form-group col-6">
            <label for="email" class="form-label">Email</label>
            <input id="email" class="form-control" type="email" name="email" required="required">
        </div>
    </div>

    <div class="row">
        <div class="form-group col-12">
            <label for="password" class="form-label">Mot de passe</label>
            <input id="password" class="form-control" type="password" name="password" required="required">
            <small id="passwordHelp" class="form-text text-muted">
                + de 10 caractères, majuscules, minuscules, chiffres, symboles</small>
        </div>
    </div>

    <div class="d-flex justify-content-center mt-4">
        <button class="btn btn-primary w-75">Valider</button>
    </div>
</form>

<?php
$content = ob_get_clean();
?>
