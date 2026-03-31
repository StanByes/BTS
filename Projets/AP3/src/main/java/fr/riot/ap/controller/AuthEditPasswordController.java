package fr.riot.ap.controller;

import fr.riot.ap.App;
import fr.riot.ap.model.Agent;
import fr.riot.ap.model.AgentDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class AuthEditPasswordController {
    @FXML
    private TextField password;

    @FXML
    private TextField newPassword;

    @FXML
    private TextField newPasswordValidation;

    @FXML
    private Label flash;

    @FXML
    private void onSubmit() {
        try {
            Agent agent = App.getInstance().getAgent();
            if (!agent.checkPassword(password.getText())) {
                flash.setText("Mot de passe actuel invalide");
                return;
            }

            String newPasswordText = newPassword.getText();
            String newPasswordValidationText = newPasswordValidation.getText();

            if (newPasswordText.length() < 10) {
                flash.setText("Le mot de passe doit faire au minimum 10 caractères");
                return;
            }

            if (!newPasswordText.equals(newPasswordValidationText)) {
                flash.setText("Les mots de passe ne correspondent pas");
                return;
            }

            agent.setPassword(newPasswordText);
            new AgentDAO().updateAgent(agent);

            App.getInstance().setAgent(agent);
            App.setRoot("home");
        } catch (SQLException exception) {
            flash.setText("Erreur interne !");
        }
    }

    @FXML
    private void back() {
        App.setRoot("home");
    }
}
