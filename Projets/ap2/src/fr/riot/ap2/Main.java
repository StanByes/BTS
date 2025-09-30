package fr.riot.ap2;

import fr.riot.ap2.controllers.HomeController;
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

        // Open Home //
        HomeController.index();

        // Make window visible //
        window.setVisible(true);
    }
}
