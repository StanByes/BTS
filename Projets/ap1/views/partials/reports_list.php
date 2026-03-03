<?php
$isSupervisor = $user->getRole()->getName() == "supervisor";
foreach ($reports as $report) {
    $note = $report->getNote();

    $headerColor = $note === 0 ? "bg-danger" : ($note === 5 ? "bg-success" : "");
    ?>
    <div class="card mb-3">
        <div class="card-header <?= $isSupervisor ? $headerColor : '' ?>">
            <div class="d-flex justify-content-between">
                <h4 class="text-center"><?= $report->getTitle() ?></h4>
                <?php
                if ($isSupervisor) {
                    ?>
                        <p class="text-center">
                            De <?= $report->getCreator()->getCompleteName() ?>
                            posté le <?= formatDateTime($report->getCreatedAt()) ?>
                        </p>
                    <?php
                } else { ?>
                        <a class="btn btn-warning" href="?action=edit_report&report_id=<?= $report->getId() ?>">
                            Éditer
                        </a>
                        <p class="text-center">Posté le <?= formatDateTime($report->getCreatedAt()) ?></p>
                <?php } ?>
            </div>
        </div>
        <div class="card-body">
            <label class="form-label" for="content">Contenu du rapport :</label>
            <textarea disabled id="content" class="form-control" rows="3"><?= $report->getContent() ?>
                    </textarea>
            <div class="d-flex justify-content-between mt-2">
                <p><strong>Date : </strong> <?= formatDate($report->getDate()) ?></p>
                <p>
                    <?php
                        if ($report->getNote() !== null) { ?>
                            <strong>Note : </strong> <?= $report->getNote() ?>/5
                        <?php } else { ?>
                            <strong>Non noté</strong>
                        <?php }
                    ?>
                </p>
            </div>
        </div>
    </div>
<?php } ?>
