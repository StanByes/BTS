<html>
    <head>
        <style>
            body {
                background-color: #718096;
            }

            h1 {
                text-align: center;
            }

            a {
                text-decoration: none;
            }
        </style>
        <title>Réinitialisation de mot de passe</title>
    </head>
    <body>
        <h1>Réinitialisation de mot de passe</h1>
        <hr>
        <h2>Pourquoi recevez-vous ceci ?</h2>
        <p>A la suite d'une demande de votre part, nous vous transmettons un <a href="{{URL::to('/reset_password')}}?code={{ $resetPasswordQuery->code }}">lien de réinitialisation de mot de passe</a>.</p>

        <h4>Si vous n'êtes pas à l'origine de cette demande, veillez à nous alerter et vérifier les accès à votre compte.</h4>
    </body>
</html>
