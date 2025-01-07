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
                <label for="title">Titre :</label>
                <input type="text" class="form-control" name="title" id="title">
            </div>

            <div class="form-group mb-3">
                <label for="content">Contenu :</label>
                <textarea class="form-control" name="content" id="content" rows="3"></textarea>
            </div>

            <div class="form-group mb-3">
                <label for="date">Date :</label>
                <input class="form-control" name="date" id="date" type="date">
            </div>

            <div class="d-flex justify-content-center mb-4">
                <button class="btn btn-success w-75">Valider</button>
            </div>
        </form>
    </div>

    <div class="tab-pane container active" id="profil">
    </div>
</div>
