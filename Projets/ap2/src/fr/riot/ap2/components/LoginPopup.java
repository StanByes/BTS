package fr.riot.ap2.components;

import fr.riot.ap2.classes.PopupValidation;
import fr.riot.ap2.entities.Client;
import fr.riot.ap2.models.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginPopup extends JFrame {
    public LoginPopup(PopupValidation<Client> callback) {
        super("Connexion");
        setSize(800, 150);
        setLocation(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, getWidth(), getHeight());

        JLabel id = new JLabel("Numéro de compte :");
        id.setFont(new Font("Serif", Font.BOLD, 15));
        id.setBounds(10, 45, 165, 25);
        panel.add(id);

        JTextField idField = new JTextField();
        idField.setBounds(160, 47, 250, 25);
        panel.add(idField);

        JButton validation = new JButton("Connexion");
        validation.addActionListener((event) -> {
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

            Client client = Model.getClientById(parsedValue);
            if (client == null) {
                callback.cancel();
                return;
            }

            callback.validate(client);
            dispose();
        });
        validation.setBounds(415, 47, 150, 25);
        panel.add(validation);

        JButton scan = new JButton("Scanner la carte d'adhérant");
        scan.setBounds(570, 47, 200, 25);
        panel.add(scan);

        getContentPane().add(panel);

        // Events //
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);

                callback.cancel();
            }
        });
    }
}
