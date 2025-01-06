<ul class="d-flex justify-content-around nav nav-tabs">
    <li class="nav-item">
        <a class="nav-link active" data-bs-toggle="tab" href="#reports">Mes Rapports</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" data-bs-toggle="tab" href="#create">Ajouter un rapport</a>
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
                        <p class="text-center"><?= $report->getCreatedAt()->format("d/m/Y à H:i") ?></p>
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
        <form method="post" action="?action=create_report">
            <div class="form-group mb-3">
                <label for="title">Titre :</label>
                <input type="text" class="form-control" name="title" id="title">
            </div>

            <div class="form-group mb-3">
                <label for="content">Contenu :</label>
                <textarea class="form-control" name="content" id="content" rows="3"></textarea>
            </div>

            <div class="d-flex justify-content-center mb-4">
                <button class="btn btn-success w-75">Valider</button>
            </div>
        </form>
    </div>
</div>
