<?php
$title = "Edition du rapport";
$card_title = $title;

ob_start();
?>

<form method="post" action="?action=update_report&report_id=<?= $report->getId() ?>">
    <div class="form-group mb-3">
        <label class="form-label" for="title">Titre :</label>
        <input type="text" class="form-control" name="title" id="title" value="<?= $report->getTitle() ?>">
    </div>

    <div class="form-group mb-3">
        <label class="form-label" for="content">Contenu :</label>
        <textarea class="form-control" name="content" id="content" rows="3"><?= $report->getContent() ?></textarea>
    </div>

    <div class="form-group mb-3">
        <label class="form-label" for="date">Date :</label>
        <input class="form-control" name="date" id="date" type="date" value="<?= formatDateForElement($report->getDate()) ?>">
    </div>

    <div class="d-flex justify-content-center mb-4">
        <button class="btn btn-success w-75">Valider</button>
    </div>
</form>

<?php
$content = ob_get_clean();
?>
