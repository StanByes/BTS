package fr.riot.ap;

import fr.riot.ap.model.Agent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Scene scene;
    private static App instance;

    public static App getInstance() {
        return instance;
    }

    private Agent agent = null;

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    @Override
    public void start(Stage stage) throws IOException {
        instance = this;

        scene = new Scene(loadFXML("auth/login"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/" + fxml + ".fxml"));
            return fxmlLoader.load();
        } catch (IOException exception) {
            System.err.println("View file not found : " + fxml);
            throw new RuntimeException(exception);
        }
    }

    public static void main(String[] args) {
        launch();
    }

}
