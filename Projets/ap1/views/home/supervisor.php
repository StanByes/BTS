<ul class="d-flex justify-content-around nav nav-tabs">
    <li class="nav-item">
        <a class="nav-link active" data-bs-toggle="tab" href="#reports">Rapports des stagiaires</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" data-bs-toggle="tab" href="#create">Liste de vos stagiaires</a>
    </li>
</ul>

<div class="tab-content mt-5">
    <div class="tab-pane container active" id="reports">
        <?php include VIEW_PATH . DS . "partials/reports_list.php" ?>
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
