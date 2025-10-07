package fr.riot.ap2.views;

import fr.riot.ap2.Main;
import fr.riot.ap2.components.BookList;
import fr.riot.ap2.controllers.HomeController;
import fr.riot.ap2.entities.Book;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BooksView extends JPanel {
    private final List<Book> books;

    public BooksView(List<Book> books) {
        this.books = books;
        initialize();
    }

    private void initialize() {
        JLabel title = new JLabel("Liste des livres");
        title.setBounds(600, 25, 450, 20);
        title.setFont(new Font("Serif", Font.BOLD, 25));

        add(title);
        add(new BookList(books, 25, 150, 1315, 500));

        JButton home = new JButton("Accueil");
        home.addActionListener((event) -> HomeController.index());
        home.setBounds(580, 665, 250, 25);
        home.setFont(new Font("Arial", Font.BOLD, 15));
        add(home);
    }
}
