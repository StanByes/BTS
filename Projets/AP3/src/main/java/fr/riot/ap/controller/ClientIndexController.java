package fr.riot.ap.controller;

import fr.riot.ap.App;
import fr.riot.ap.model.Client;
import fr.riot.ap.model.ClientDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientIndexController {
    @FXML
    private ListView<String> list;

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

    private List<Client> clients;
    private List<Control> controls = new ArrayList<>();

    private Client selectedClient = null;

    public void initialize() {
        controls.add(name);
        controls.add(firstname);
        controls.add(man);
        controls.add(woman);
        controls.add(other);
        controls.add(unknown);
        controls.add(birthdate);
        controls.add(category);
        controls.add(address);
        controls.add(phone);
        controls.add(email);

        // Group genre radio buttons
        genreGroup = new ToggleGroup();
        man.setToggleGroup(genreGroup);
        woman.setToggleGroup(genreGroup);
        other.setToggleGroup(genreGroup);
        unknown.setToggleGroup(genreGroup);

        // Load client list
        try {
            ClientDAO clientDAO = new ClientDAO();
            clients = clientDAO.getAll();

            for (Client client : clients)
                list.getItems().add(client.getCompleteName());
        } catch (SQLException exception) {
            // Throw an error !
            return;
        }

        // Handle list choice change
        list.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            selectedClient = clients.get(newValue.intValue());

            name.setText(selectedClient.getName());
            firstname.setText(selectedClient.getFirstname());
            category.setText(selectedClient.getCategory());
            address.setText(selectedClient.getAddress());
            phone.setText(selectedClient.getPhone());
            email.setText(selectedClient.getEmail());

            switch (selectedClient.getGenre()) {
                case MAN -> genreGroup.selectToggle(man);
                case WOMAN -> genreGroup.selectToggle(woman);
                case OTHER-> genreGroup.selectToggle(other);
                default -> genreGroup.selectToggle(unknown);
            }

            birthdate.setValue(selectedClient.getBirthdate().toLocalDate());

            setControlsState(true);
        });
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

        selectedClient.setName(name);
        selectedClient.setFirstname(firstname);
        selectedClient.setGenre(genre);
        selectedClient.setBirthdate(finalBirthdate);
        selectedClient.setCategory(category);
        selectedClient.setAddress(address);
        selectedClient.setPhone(phone);
        selectedClient.setEmail(email);

        try {
            ClientDAO clientDAO = new ClientDAO();
            clientDAO.updateClient(selectedClient);
        } catch (SQLException exception) {
            // Throw an error !
            throw new RuntimeException(exception);

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

    private void setControlsState(boolean state) {
        for (Control control : controls)
            control.setDisable(!state);
    }
}
