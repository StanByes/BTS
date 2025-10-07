package fr.riot.ap2.controllers;

import fr.riot.ap2.Main;
import fr.riot.ap2.models.Model;
import fr.riot.ap2.views.AccountView;

public class AccountController {
    public static void index() {
        Model.load();

        Main.getWindow().linkView(new AccountView());
    }
}
