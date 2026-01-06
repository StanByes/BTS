package fr.riot.ap.controller;

import fr.riot.ap.App;
import fr.riot.ap.model.Client;
import fr.riot.ap.model.ClientDAO;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class ClientCreateController {
    @FXML
    private TextField name;

    @FXML
    private TextField firstname;

    @FXML
    private TextField category;

    @FXML
    private TextField address;

    @FXML
    private TextField phone;

    @FXML
    private TextField email;

    private ToggleGroup genreGroup;
    @FXML
    private RadioButton man;
    @FXML
    private RadioButton woman;
    @FXML
    private RadioButton other;
    @FXML
    private RadioButton unknown;

    @FXML
    private DatePicker birthdate;


    public void initialize() {
        genreGroup = new ToggleGroup();

        man.setToggleGroup(genreGroup);
        woman.setToggleGroup(genreGroup);
        other.setToggleGroup(genreGroup);
        unknown.setToggleGroup(genreGroup);
    }

    @FXML
    public void submitForm() {
        String name = this.name.getText();
        String firstname = this.firstname.getText();
        String category = this.category.getText();
        String address = this.address.getText();
        String phone = this.phone.getText();
        String email = this.email.getText();

        if (isEmpty(name) || isEmpty(firstname) || isEmpty(category) || isEmpty(address) || isEmpty(phone) || isEmpty(email)) {
            // Throw error !!
            return;
        }

        RadioButton selectedGenre = (RadioButton) genreGroup.getSelectedToggle();
        Client.Genre genre;
        switch (selectedGenre.getId()) {
            case "man" -> genre = Client.Genre.MAN;
            case "woman" -> genre = Client.Genre.WOMAN;
            case "other"-> genre = Client.Genre.OTHER;
            default -> genre = Client.Genre.UNKNOWN;
        }

        LocalDate selectedBirthdate = this.birthdate.getValue();
        if (selectedBirthdate == null) {
            // Throw an error !
            return;
        }
        Date finalBirthdate = Date.valueOf(selectedBirthdate);

        try {
            ClientDAO clientDAO = new ClientDAO();
            clientDAO.insertClient(name, firstname, genre, finalBirthdate, category, address, phone, email);
        } catch (SQLException exception) {
            // Throw an error !
            return;
        }

        // Add flash success
        back();
    }

    @FXML
    public void back() {
        App.setRoot("home");
    }

    private boolean isEmpty(String v) {
        return v == null || v.isBlank();
    }
}
