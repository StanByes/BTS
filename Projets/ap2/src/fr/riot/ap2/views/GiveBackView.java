package fr.riot.ap2.views;

import fr.riot.ap2.Main;
import fr.riot.ap2.classes.PopupValidation;
import fr.riot.ap2.components.LoginPopup;
import fr.riot.ap2.components.Scanner;
import fr.riot.ap2.controllers.HomeController;
import fr.riot.ap2.entities.Book;
import fr.riot.ap2.entities.Client;
import fr.riot.ap2.models.BookModel;
import fr.riot.ap2.models.Model;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class GiveBackView extends JPanel {
    private JPanel bookPanel = null;

    public GiveBackView() {
        UIManager.put("Button.disabledText", Color.WHITE);

        JLabel id = new JLabel("ISBN du livre :");
        id.setFont(new Font("Serif", Font.BOLD, 15));
        id.setBounds(125, 60, 165, 25);
        add(id);

        JTextField idField = new JTextField();
        idField.setBounds(230, 62, 250, 25);
        add(idField);

        JButton validation = new JButton("Valider");
        validation.addActionListener((event) -> {
            // Search book
            String value = idField.getText();
            handleBook(value);
        });
        validation.setBounds(485, 62, 150, 25);
        add(validation);

        JButton home = new JButton("Accueil");
        home.addActionListener((event) -> HomeController.index());
        home.setBounds(580, 665, 250, 25);
        home.setFont(new Font("Arial", Font.BOLD, 15));
        add(home);

        JButton scan = new JButton("Scanner le livre");
        scan.setBounds(1045, 62, 220, 25);
        scan.addActionListener(event -> {
            home.setEnabled(false);
            validation.setEnabled(false);

            new Scanner(new PopupValidation<String>() {
                @Override
                public void validate(String value) {
                    home.setEnabled(true);
                    validation.setEnabled(true);

                    handleBook(value);
                }

                @Override
                public void cancel() {
                    home.setEnabled(true);
                    validation.setEnabled(true);
                }
            });
        });
        add(scan);
    }

    public void setPanel(JPanel panel) {
        if (bookPanel != null)
            remove(bookPanel);

        bookPanel = panel;
        add(panel);

        validate();
        repaint();
    }

    private void handleBook(String value) {
        if (value == null || value.isEmpty())
            return;

        Book book = Model.getBookByISBN(value);
        if (book != null) {
            setPanel(createBookInformationPanel(book));
        }
    }

    private JPanel createBookInformationPanel(Book book) {
        JPanel bookPanel = new JPanel();
        bookPanel.setLayout(null);
        bookPanel.setBounds(125, 100, Main.getWindow().getWidth() - 240, Main.getWindow().getHeight() - 200);
        bookPanel.setBorder(BorderFactory.createDashedBorder(Color.BLACK));

        JLabel name = new JLabel("Nom : " + book.getName());
        name.setBounds(25, 25, 450, 25);
        name.setFont(new Font("Arial", Font.PLAIN, 25));
        name.setHorizontalAlignment(SwingConstants.LEFT);
        bookPanel.add(name);

        JLabel author = new JLabel("Auteur : " + book.getAuthor().getFullName());
        author.setBounds(25, 75, 450, 25);
        author.setFont(new Font("Arial", Font.PLAIN, 25));
        author.setHorizontalAlignment(SwingConstants.LEFT);
        bookPanel.add(author);

        JLabel publishedAt = new JLabel("Publié le : " + Main.formatDate(book.getPublishedAt()));
        publishedAt.setBounds(845, 25, 450, 25);
        publishedAt.setFont(new Font("Arial", Font.PLAIN, 25));
        publishedAt.setHorizontalAlignment(SwingConstants.LEFT);
        bookPanel.add(publishedAt);

        JLabel isbn = new JLabel("ISBN : " + book.getISBN());
        isbn.setBounds(845, 75, 450, 25);
        isbn.setFont(new Font("Arial", Font.PLAIN, 25));
        isbn.setHorizontalAlignment(SwingConstants.LEFT);
        bookPanel.add(isbn);

        boolean notBorrow = book.getClient() == null;
        JButton giveBack = new JButton(notBorrow ? "Le livre n'est pas emprunté" : "Retourner");
        giveBack.setBounds(notBorrow ? 425 : 505, 480, notBorrow ? 320 : 150, 40);
        giveBack.setEnabled(!notBorrow);
        giveBack.setBackground(notBorrow ? new Color(255, 10, 0) : null);
        giveBack.setFont(new Font("Arial", Font.BOLD, 20));

        if (!notBorrow) {
            GiveBackView panel = this;
            giveBack.addActionListener((event) -> {
                giveBack.setEnabled(false);

                new LoginPopup(new PopupValidation<Client>() {
                    @Override
                    public void validate(Client client) {
                        if (book.getClient().getId() == client.getId()) {
                            book.setClient(null);
                            try {
                                BookModel.updateBookClient(book);
                            } catch (SQLException exception) {
                                // TODO : Add flash error
                                throw new RuntimeException(exception);
                            }

                            panel.setPanel(validationPanel());
                        } else {
                            panel.setPanel(notOwnerPanel());
                        }
                    }

                    @Override
                    public void cancel() {
                        giveBack.setEnabled(true);
                    }
                });
            });
        }

        bookPanel.add(giveBack);
        return bookPanel;
    }

    private JPanel notOwnerPanel() {
        JPanel notOwner = new JPanel();
        notOwner.setLayout(null);
        notOwner.setBounds(125, 100, Main.getWindow().getWidth() - 240, Main.getWindow().getHeight() - 200);
        notOwner.setBorder(BorderFactory.createDashedBorder(Color.BLACK));

        JLabel text = new JLabel("Ce livre n'a pas été emprunté par cet adhérent");
        text.setForeground(new Color(175, 0, 0));
        text.setBounds(0, 0, notOwner.getWidth(), notOwner.getHeight());
        text.setFont(new Font("Arial", Font.BOLD, 25));
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setVerticalAlignment(SwingConstants.CENTER);
        notOwner.add(text);

        return notOwner;
    }

    private JPanel validationPanel() {
        JPanel validation = new JPanel();
        validation.setLayout(null);
        validation.setBounds(125, 100, Main.getWindow().getWidth() - 240, Main.getWindow().getHeight() - 200);
        validation.setBorder(BorderFactory.createDashedBorder(Color.BLACK));

        JLabel text = new JLabel("Livre retourné avec succès");
        text.setForeground(new Color(0, 135, 0));
        text.setBounds(0, 0, validation.getWidth(), validation.getHeight());
        text.setFont(new Font("Arial", Font.BOLD, 25));
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setVerticalAlignment(SwingConstants.CENTER);
        validation.add(text);

        return validation;
    }
}
