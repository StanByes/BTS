<ul class="d-flex justify-content-around nav nav-tabs">
    <li class="nav-item">
        <a class="nav-link active" data-bs-toggle="tab" href="#reports">Rapports des stagiaires</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" data-bs-toggle="tab" href="#create">Liste de vos stagiaires</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" data-bs-toggle="tab" href="#stats">Statistiques</a>
    </li>
</ul>

<div class="tab-content mt-5">
    <div class="tab-pane container active" id="reports">
        <?php
        if ($specificUser) {
            ?>
                <div class="d-flex justify-content-center">
                    <a href="./" class="btn btn-danger mb-4">Revenir à tous les rapports</a>
                </div>
            <?php
        }
        ?>
        <?php include VIEW_PATH . DS . "partials/reports_list.php" ?>
    </div>

    <div class="tab-pane container fade" id="create">
        <?php
        foreach ($internships as $internship) {
            $intern = $internship->getIntern();
            ?>
            <div class="card mb-3">
                <div class="card-header" data-bs-toggle="collapse" data-bs-target="#intern_<?= $intern->getId() ?>">
                    <div class="d-flex justify-content-center">
                        <h4 class="text-center"><?= $intern->getCompleteName() ?></h4>
                    </div>
                </div>
                <div class="card-body collapse" id="intern_<?= $intern->getId() ?>">
                    <div class="d-flex justify-content-between">
                        <div>
                            <h3 class="mb-4">Informations du stagiaire</h3>
                            <p><strong>Nom :</strong> <?= $intern->getSurname() ?></p>
                            <p><strong>Prénom :</strong> <?= $intern->getFirstname() ?></p>
                            <p><strong>Login :</strong> <?= $intern->getLogin() ?></p>
                            <p><strong>Adresse Mail :</strong> <?= $intern->getMail() ?></p>
                        </div>
                        <a class="btn btn-primary h-25" href="?user=<?= $intern->getId() ?>">Voir les rapports</a>
                    </div>

                    <hr>

                    <h3 class="mb-4">Informations du stage</h3>
                    <p><strong>Date de début :</strong> <?= formatDate($internship->getStartAt()) ?></p>
                    <p><strong>Date de fin :</strong> <?= formatDate($internship->getEndAt()) ?></p>
                    <p>
                        <strong>Heure d'arrivée journalière:</strong>
                        <?= $internship->getDayStartAt() !== null
                            ? formatTime($internship->getDayStartAt())
                            : "Non défini" ?>
                    </p>
                    <p>
                        <strong>Heure de départ journalière :</strong>
                        <?= $internship->getDayEndAt() !== null
                            ? formatTime($internship->getDayEndAt())
                            : "Non défini" ?>
                    </p>
                </div>
            </div>
        <?php } ?>
    </div>

    <div class="tab-pane container active" id="stats">
        <div class="card mb-3">
            <div class="card-body">
                <p>
                    <strong>Nombre d'élèves à votre charge :</strong>
                    <?= count($internships) ?>
                </p>
                <p>
                    <strong>Élèves sans compte rendu :</strong>
                    <ul>
                        <?php foreach($withoutReportCount as $user) { ?>
                            <li><?= $user->getFirstname() . " " . $user->getSurname() ?></li>
                        <?php } ?>
                    </ul>
                </p>
            </div>
        </div>
    </div>
</div>
