package fr.riot.ap.controller;

import fr.riot.ap.App;
import javafx.fxml.FXML;

public class HomeController {
    @FXML
    public void showAccountCreate() {
        App.setRoot("accounts/create");
    }

    @FXML
    public void showAccounts() {
        App.setRoot("accounts/index");
    }

    @FXML
    public void showClientCreate() {
        App.setRoot("clients/create");
    }

    @FXML
    public void showClients() {
        App.setRoot("clients/index");
    }
}
