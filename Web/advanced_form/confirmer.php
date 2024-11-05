<?php
session_start();

?>

<html>
    <head>
        <title>Confirmée</title>
    </head>
    <body>
        <p>Inscription validée</p>
        <?php
            foreach ($_SESSION as $key => $value) { ?>
                <p><?= $key ?> : <?= $value ?></p>
        <?php } ?>
    </body>
</html>