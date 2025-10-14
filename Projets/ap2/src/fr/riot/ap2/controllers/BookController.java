package fr.riot.ap2.controllers;

import fr.riot.ap2.Main;
import fr.riot.ap2.entities.Book;
import fr.riot.ap2.models.Model;
import fr.riot.ap2.views.BooksView;
import fr.riot.ap2.views.BorrowView;

import java.util.List;

public class BookController {
    public static void index() {
        Model.load();

        List<Book> books = Model.getBooks();
        Main.getWindow().linkView(new BooksView(books));
    }

    public static void borrow() {
        Model.load();
        Main.getWindow().linkView(new BorrowView());
    }
}
