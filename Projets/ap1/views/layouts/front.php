<html lang="fr">
    <head>
        <title><?= $title ?></title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>

    <body>
        <header>
            <nav class="nav d-flex justify-content-end">
                <a class="btn btn-close rounded bg-danger m-2" href="./?action=logout"></a>
            </nav>
        </header>
        <main class="mt-4">
            <div class="container-fluid">
                <div class="card mx-auto <?= $card_size ?? "w-50" ?>">
                    <div class="card-header">
                        <h4 class="ms-2 text-center"><?= $card_title ?></h4>
                    </div>

                    <div class="card-body">
                        <?= $content ?>
                    </div>
                </div>
            </div>
        </main>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>
    </body>
</html>
