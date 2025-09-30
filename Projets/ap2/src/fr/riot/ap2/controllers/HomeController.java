package fr.riot.ap2.controllers;

import fr.riot.ap2.Main;
import fr.riot.ap2.views.HomeView;

public class HomeController {
    public static void index() {
        Main.getWindow().linkView(new HomeView());
    }
}
