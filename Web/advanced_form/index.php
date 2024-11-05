<!doctype HTML>
<html>
    <head>
        <title>Advanced Forms</title>
        <link rel="stylesheet" href="assets/style.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
    </head>

    <body>
        <div class="form">
            <h1>Formulaire</h1>
            <form action="accueil.php" method="POST">
                <div class="form-group">
                    <label for="login">Login:</label>
                    <input required="required" id="login" type="text" name="login" placeholder="Entrez votre login">
                </div>

                <div class="form-group">
                    <label for="password">Mot de passe:</label>
                    <input required="required" id="password" type="password" name="password" placeholder="Entrez votre mot de passe">
                </div>

                <div class="form-group">
                    <span>Genre:</span>
                    <br>
                    <input required="required" type="radio" id="man" name="gender" value="man">
                    <label for="man">Homme</label>

                    <input required="required" type="radio" id="woman" name="gender" value="woman">
                    <label for="woman">Femme</label>
                </div>

                <div class="form-group">
                    <label for="category">Catégorie:</label>
                    <select required="required" id="category" name="category">
                        <option value="1" selected>Non renseigné</option>
                        <option value="2">Ouvrier</option>
                        <option value="3">Cadre</option>
                        <option value="4">Sans-emploi</option>
                        <option value="5">Autres</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="description">Description:</label>
                    <textarea required="required" id="description" name="description" placeholder="Entrez une description"></textarea>
                </div>

                <div class="form-group">
                    <label for="name">Nom:</label>
                    <input required="required" type="text" id="name" name="name" placeholder="Entrez votre nom">
                </div>

                <div class="form-group">
                    <label for="mail">Email:</label>
                    <input required="required" type="email" id="mail" name="mail" placeholder="Entrez votre email">
                </div>

                <div class="form-submit">
                    <button type="submit">OK</button>
                </div>
            </form>
        </div>
    </body>
</html>
