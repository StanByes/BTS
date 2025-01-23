<?php
$interns = array_filter($users, function ($u): bool {
    return $u->getRole()->getName() == "intern";
});

$supervisors = array_filter($users, function ($u): bool {
    return $u->getRole()->getName() == "supervisor";
});

?>

<h1>Informations</h1>
<h2>Nombre total de CR : <?= $totalReports ?></h2>

<hr>

<h2>Liste des élèves (<?= count($interns) ?>)</h2>
<table class="table table-responsive">
    <thead>
        <tr>
            <th>#</th>
            <th>Nom</th>
            <th>Prénom</th>
            <th>Mail</th>
            <th>Nombre de CR</th>
        </tr>
    </thead>

    <tbody>
        <?php foreach ($interns as $user) { ?>
            <tr>
                <td><?= $user->getId() ?></td>
                <td><?= $user->getFirstname() ?></td>
                <td><?= $user->getSurname() ?></td>
                <td><?= $user->getMail() ?></td>
                <td><?= $reportsCountByUsers[$user->getId()] ?></td>
            </tr>
        <?php } ?>
    </tbody>
</table>

<hr>

<h2>Liste des maîtres de stage (<?= count($supervisors) ?>)</h2>
<table class="table table-responsive">
    <thead>
    <tr>
        <th>#</th>
        <th>Nom</th>
        <th>Prénom</th>
        <th>Mail</th>
    </tr>
    </thead>

    <tbody>
    <?php foreach ($supervisors as $user) { ?>
        <tr>
            <td><?= $user->getId() ?></td>
            <td><?= $user->getFirstname() ?></td>
            <td><?= $user->getSurname() ?></td>
            <td><?= $user->getMail() ?></td>
        </tr>
    <?php } ?>
    </tbody>
</table>
