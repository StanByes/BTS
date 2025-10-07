package fr.riot.ap2.components;

import fr.riot.ap2.entities.Book;

import javax.swing.*;
import java.util.List;

public class BookList extends JScrollPane {
    public BookList(List<Book> books, int x, int y, int width, int height) {
        JList<String> bookList = new JList<>();
        bookList.setFixedCellHeight(30);
        DefaultListModel<String> bookListModel = new DefaultListModel<>();
        for (Book book : books)
            bookListModel.addElement(book.getName() + " - " + book.getAuthor().getFullName() + " (" + book.getISBN() + ")");
        bookList.setModel(bookListModel);

        setBounds(x, y, width, height);
        add(bookList);
        setViewportView(bookList);
    }
}
