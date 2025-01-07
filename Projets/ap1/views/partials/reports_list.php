<?php
foreach ($reports as $report) {?>
    <div class="card mb-3">
        <div class="card-header">
            <div class="d-flex justify-content-between">
                <h4 class="text-center"><?= $report->getTitle() ?></h4>
                <?php
                if ($user->getRole()->getName() == "supervisor") {
                    ?>
                        <p class="text-center">
                            De <?= $report->getCreator()->getCompleteName() ?>
                            posté le <?= formatDateTime($report->getCreatedAt()) ?>
                        </p>
                    <?php
                } else { ?>
                        <p class="text-center">Posté le <?= formatDateTime($report->getCreatedAt()) ?></p>
                <?php } ?>
            </div>
        </div>
        <div class="card-body">
            <label class="form-label" for="content">Contenu du rapport :</label>
            <textarea disabled id="content" class="form-control" rows="3"><?= $report->getContent() ?>
                    </textarea>
            <p class="mt-2"><strong>Date : </strong> <?= formatDate($report->getDate()) ?></p>
        </div>
    </div>
<?php } ?>
