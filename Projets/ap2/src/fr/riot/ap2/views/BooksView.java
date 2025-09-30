package fr.riot.ap2.views;

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

        JList<String> bookList = new JList<>();
        bookList.setFixedCellHeight(30);
        DefaultListModel<String> bookListModel = new DefaultListModel<>();
        for (Book book : books)
            bookListModel.addElement(book.getName() + " - " + book.getAuthor().getFullName() + " (" + book.getISBN() + ")");
        bookList.setModel(bookListModel);

        JScrollPane scrollPane = new JScrollPane(bookList);
        scrollPane.setBounds(25, 150, 1315, 500);
        add(scrollPane);
    }
}
