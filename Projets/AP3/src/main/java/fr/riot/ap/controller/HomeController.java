package fr.riot.ap.controller;

import fr.riot.ap.App;
import fr.riot.ap.model.AgentDAO;
import javafx.fxml.FXML;

import java.sql.SQLException;

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

    @FXML
    public void showAuthEditPassword() {
        App.setRoot("auth/edit_password");
    }
}
