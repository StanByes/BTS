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
                            le <?= formatDateTime($report->getCreatedAt()) ?>
                        </p>
                    <?php
                } else { ?>
                        <p class="text-center"><?= formatDateTime($report->getCreatedAt()) ?></p>
                <?php } ?>
            </div>
        </div>
        <div class="card-body">
            <label class="form-label" for="content">Contenu du rapport :</label>
            <textarea disabled id="content" class="form-control" rows="3"><?= $report->getContent() ?>
                    </textarea>
        </div>
    </div>
<?php } ?>
