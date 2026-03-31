<?php
use App\Entities\FrontDesign;
?>

<ul class="d-flex justify-content-around nav nav-tabs">
    <li class="nav-item">
        <a class="nav-link active" data-bs-toggle="tab" href="#reports">Mes Rapports</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" data-bs-toggle="tab" href="#create">Ajouter un rapport</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" data-bs-toggle="tab" href="#profil">Modifier mon profil</a>
    </li>
</ul>

<div class="tab-content mt-5">
    <div class="tab-pane container active" id="reports">
        <?php include VIEW_PATH . DS . "partials/reports_list.php" ?>
    </div>

    <div class="tab-pane container fade" id="create">
        <form method="post" action="?action=create_report">
            <div class="form-group mb-3">
                <label class="form-label" for="title">Titre :</label>
                <input type="text" class="form-control" name="title" id="title" required>
            </div>

            <div class="form-group mb-3">
                <label class="form-label" for="content">Contenu :</label>
                <textarea class="form-control" name="content" id="content" required rows="3"></textarea>
            </div>

            <div class="form-group mb-3">
                <label class="form-label" for="note">Note (optionnel) :</label>
                <select class="form-select" name="note" id="note">
                    <option <?= $report->getNote() == null ? "selected" : "" ?> value="">Non noté</option>
                    <?php
                    for ($i = 0; $i < 6; $i++) { ?>
                        <option <?= $report->getNote() === $i ? "selected" : "" ?> value="<?= $i ?>"><?= $i ?></option>
                    <?php }
                    ?>
                </select>
            </div>

            <div class="form-group mb-3">
                <label class="form-label" for="date">Date :</label>
                <input class="form-control" name="date" id="date" type="date" required>
            </div>

            <div class="d-flex justify-content-center mb-4">
                <button class="btn btn-success w-75">Valider</button>
            </div>
        </form>
    </div>

    <div class="tab-pane container fade" id="profil">
        <form method="post" action="?action=edit_user">
            <div class="row">
                <div class="form-group col-6 mb-3">
                    <label class="form-label" for="surname">Nom :</label>
                    <input class="form-control" name="surname" id="surname" required="required"
                           value="<?= $user->getSurname() ?>">
                </div>

                <div class="form-group col-6 mb-3">
                    <label class="form-label" for="firstname">Prénom :</label>
                    <input class="form-control" name="firstname" id="firstname" required="required"
                           value="<?= $user->getFirstname() ?>">
                </div>

                <div class="form-group col-6 mb-3">
                    <label class="form-label" for="mail">Adresse mail :</label>
                    <input type="email" class="form-control" name="mail" id="mail" required="required"
                           value="<?= $user->getMail() ?>">
                </div>

		<div class="form-check form-switch col-6 mb-3">
		    <input class="form-check-input" type="checkbox" role="switch" name="front_design" id="switchFrontDesign" <?= $user->getFrontDesign() == FrontDesign::DARK ? 'checked' : '' ?>/>
  		    <label class="form-check-label" for="switchFrontDesign">Mode sombre</label>
		</div>
            </div>

            <?php
            if (isset($internship)) {
                ?>
            <hr>

            <div class="row">
                <div class="form-group col-6 mb-3">
                    <label class="form-label" for="start_at">Date de début de stage :</label>
                    <input type="date" class="form-control" name="start_at" id="start_at" required="required"
                           value="<?= formatDateForElement($internship->getStartAt()) ?>">
                </div>

                <div class="form-group col-6">
                    <label class="form-label" for="end_at">Date de fin de stage :</label>
                    <input type="date" class="form-control" name="end_at" id="end_at" required="required"
                           value="<?= formatDateForElement($internship->getEndAt()) ?>">
                </div>

                <div class="form-group col-6 mb-3">
                    <label class="form-label" for="day_start_at">Heure de début de la journée :</label>
                    <input type="time" class="form-control" name="day_start_at" id="day_start_at"
                       <?= $internship->getDayStartAt() !== null
                           ? "value='" . formatTimeForElement($internship->getDayStartAt(), false) . "'"
                           : "" ?>">
                </div>

                <div class="form-group col-6 mb-3">
                    <label class="form-label" for="day_end_at">Heure de fin de la journée :</label>
                    <input type="time" class="form-control" name="day_end_at" id="day_end_at"
                        <?= $internship->getDayEndAt() !== null
                           ? "value='" . formatTimeForElement($internship->getDayEndAt(), false) . "'"
                           : "" ?>">
                </div>

                <?php
            }
            ?>

                <div class="d-flex justify-content-center mt-4">
                    <button class="btn btn-success w-75">Valider</button>
                </div>
            </div>
        </form>
    </div>
</div>
