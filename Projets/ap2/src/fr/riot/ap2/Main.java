package fr.riot.ap2;

import fr.riot.ap2.controllers.BookController;
import fr.riot.ap2.controllers.HomeController;
import fr.riot.ap2.models.Model;
import fr.riot.ap2.views.Window;

import java.awt.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class Main {
    private static final SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");
    private static Window window;

    public static String formatDate(Date date) {
        return dateParser.format(date);
    }
    public static Window getWindow() {
        return window;
    }

    public static void main(String[] args) {
        // Initializers //
        window = new Window();

        // First database load //
        Model.load();

        // Open Home //
        HomeController.index();

        // Make window visible //
        window.setVisible(true);
    }
}
