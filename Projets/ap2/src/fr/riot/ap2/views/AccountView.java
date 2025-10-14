package fr.riot.ap2.views;

import fr.riot.ap2.Main;
import fr.riot.ap2.components.BookList;
import fr.riot.ap2.controllers.HomeController;
import fr.riot.ap2.entities.Client;
import fr.riot.ap2.models.ClientModel;
import fr.riot.ap2.models.Model;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AccountView extends JPanel {
    private Client client = null;
    private JPanel accountPanel = null;

    public AccountView() {
        JLabel id = new JLabel("Numéro de compte :");
        id.setFont(new Font("Serif", Font.BOLD, 15));
        id.setBounds(125, 60, 165, 25);
        add(id);

        JTextField idField = new JTextField();
        idField.setBounds(265, 62, 250, 25);
        add(idField);

        JButton validation = new JButton("Connexion");
        validation.addActionListener((event) -> {
            if (client != null) {
                // Logout
                remove(accountPanel);
                idField.setEnabled(true);
                validation.setText("Connexion");
                validate();
                repaint();

                client = null;
                return;
            }

            // Login
            String value = idField.getText();
            if (value == null || value.isEmpty())
                return;

            int parsedValue;
            try {
                parsedValue = Integer.parseInt(value);
            } catch (NumberFormatException exception) {
                return;
            }

            client = Model.getClientById(parsedValue);
            if (client != null) {
                idField.setEnabled(false);
                validation.setText("Déconnexion");

                accountPanel = createAccountInformationPanel(client);
                add(accountPanel);
                validate();
                repaint();
            }
        });
        validation.setBounds(520, 62, 150, 25);
        add(validation);

        JButton scan = new JButton("Scanner la carte d'adhérant");
        scan.setBounds(1045, 62, 220, 25);
        add(scan);

        JButton home = new JButton("Accueil");
        home.addActionListener((event) -> HomeController.index());
        home.setBounds(580, 665, 250, 25);
        home.setFont(new Font("Arial", Font.BOLD, 15));
        add(home);
    }

    private JPanel createAccountInformationPanel(Client client) {
        JPanel clientPanel = new JPanel();
        clientPanel.setLayout(null);
        clientPanel.setBounds(125, 100, Main.getWindow().getWidth() - 240, Main.getWindow().getHeight() - 200);
        clientPanel.setBorder(BorderFactory.createDashedBorder(Color.BLACK));

        JLabel name = new JLabel("Nom :");
        name.setBounds(25, 25, 150, 25);
        name.setFont(new Font("Arial", Font.PLAIN, 25));
        name.setHorizontalAlignment(SwingConstants.LEFT);
        clientPanel.add(name);

        JTextField nameField = new JTextField();
        nameField.setText(client.getLastName());
        nameField.setBounds(145, 27, 250, 25);
        clientPanel.add(nameField);

        JLabel firstName = new JLabel("Prénom :");
        firstName.setBounds(25, 75, 150, 25);
        firstName.setFont(new Font("Arial", Font.PLAIN, 25));
        firstName.setHorizontalAlignment(SwingConstants.LEFT);
        clientPanel.add(firstName);

        JTextField firstNameField = new JTextField();
        firstNameField.setText(client.getFirstName());
        firstNameField.setBounds(145, 77, 250, 25);
        clientPanel.add(firstNameField);

        JLabel email = new JLabel("Email :");
        email.setBounds(675, 25, 150, 25);
        email.setFont(new Font("Arial", Font.PLAIN, 25));
        email.setHorizontalAlignment(SwingConstants.LEFT);
        clientPanel.add(email);

        JTextField emailField = new JTextField();
        emailField.setText(client.getEmail());
        emailField.setBounds(825, 27, 250, 25);
        clientPanel.add(emailField);

        JLabel phone = new JLabel("Téléphone :");
        phone.setBounds(675, 75, 150, 25);
        phone.setFont(new Font("Arial", Font.PLAIN, 25));
        phone.setHorizontalAlignment(SwingConstants.LEFT);
        clientPanel.add(phone);

        JTextField phoneField = new JTextField();
        phoneField.setText(client.getPhone());
        phoneField.setBounds(825, 77, 250, 25);
        clientPanel.add(phoneField);

        JButton validation = new JButton("Valider");
        validation.addActionListener((event) -> {
            String lastNameValue = nameField.getText();
            String firstNameValue = firstNameField.getText();
            String emailValue = emailField.getText();
            String phoneValue = phoneField.getText();

            if (isBadValue(lastNameValue) || isBadValue(firstNameValue) || isBadValue(emailValue) || isBadValue(phoneValue)) {
                // TODO : Add flash error
                return;
            }

            client.setLastName(lastNameValue);
            client.setFirstName(firstNameValue);
            client.setEmail(emailValue);
            client.setPhone(phoneValue);

            try {
                ClientModel.updateClient(client);
            } catch (SQLException exception) {
                // TODO : Add flash error
                throw new RuntimeException(exception);
            }
        });
        validation.setBounds(960, 125, 115, 25);
        clientPanel.add(validation);

        JLabel books = new JLabel("Liste de mes livres :");
        books.setBounds(25, 125, 300, 25);
        books.setFont(new Font("Arial", Font.BOLD, 25));
        books.setHorizontalAlignment(SwingConstants.LEFT);
        clientPanel.add(books);

        clientPanel.add(
            new BookList(
                client.getBooks(),
                25,
                155,
                clientPanel.getWidth() - 50,
                clientPanel.getHeight() - 165
            )
        );
        return clientPanel;
    }

    private boolean isBadValue(String v) {
        return v == null || v.isEmpty() || v.equals(" ");
    }
}
