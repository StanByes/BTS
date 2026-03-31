package fr.riot.ap.controller;

import fr.riot.ap.App;
import fr.riot.ap.model.Agent;
import fr.riot.ap.model.AgentDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class AuthLoginController {
    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Label flash;

    @FXML
    private void onSubmit() {
        try {
            Agent agent = new AgentDAO().getAgentByUsername(username.getText());

            if (agent == null) {
                flash.setText("Nom d'utilisateur invalide");
                return;
            }

            if (!agent.checkPassword(password.getText())) {
                flash.setText("Mot de passe invalide");
                return;
            }

            App.getInstance().setAgent(agent);
            App.setRoot("home");
        } catch (SQLException exception) {
            flash.setText("Erreur interne !");
            throw new RuntimeException(exception);
        }
    }

    @FXML
    public void generatePasswords() {
        try {
            new AgentDAO().generatePasswordForAgentsWithoutPassword();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
