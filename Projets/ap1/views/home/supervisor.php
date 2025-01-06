<ul class="d-flex justify-content-around nav nav-tabs">
    <li class="nav-item">
        <a class="nav-link active" data-bs-toggle="tab" href="#reports">Rapports des stagiaires</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" data-bs-toggle="tab" href="#create">Liste des stagiaires</a>
    </li>
</ul>

<div class="tab-content mt-5">
    <div class="tab-pane container active" id="reports">
        <?php
        foreach ($reports as $report) {?>
            <div class="card mb-3">
                <div class="card-header">
                    <div class="d-flex justify-content-between">
                        <h4 class="text-center"><?= $report->getTitle() ?></h4>
                        <p class="text-center">
                            De <?= $report->getCreator()->getCompleteName() ?>
                            le <?= $report->getCreatedAt()->format("d/m/Y à H:i") ?>
                        </p>
                    </div>
                </div>
                <div class="card-body">
                    <label class="form-label" for="content">Contenu du rapport :</label>
                    <textarea disabled id="content" class="form-control" rows="3"><?= $report->getContent() ?>
                    </textarea>
                </div>
            </div>
        <?php } ?>
    </div>

    <div class="tab-pane container fade" id="create">
        <?php
        foreach ($internships as $internship) {
            $intern = $internship->getIntern();
            ?>
            <div class="card mb-3">
                <div class="card-header">
                    <div class="d-flex justify-content-center">
                        <h4 class="text-center"><?= $intern->getCompleteName() ?></h4>
                    </div>
                </div>
                <div class="card-body">
                    <p><strong>Nom :</strong> <?= $intern->getSurname() ?></p>
                    <p><strong>Prénom :</strong> <?= $intern->getFirstname() ?></p>
                    <p><strong>Login :</strong> <?= $intern->getLogin() ?></p>
                    <p><strong>Adresse Mail :</strong> <?= $intern->getMail() ?></p>
                </div>
            </div>
        <?php } ?>
    </div>
</div>
