package fr.riot.ap2;

import fr.riot.ap2.controllers.AccountController;
import fr.riot.ap2.models.Model;
import fr.riot.ap2.views.Window;

import java.awt.*;

public class Main {
    private static Window window;

    public static Window getWindow() {
        return window;
    }

    public static void main(String[] args) {
        // Initializers //
        window = new Window();

        // First database load //
        Model.load();

        // Open Home //
        AccountController.index();

        // Make window visible //
        window.setVisible(true);
    }
}
