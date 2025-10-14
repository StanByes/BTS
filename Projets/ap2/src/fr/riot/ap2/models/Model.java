package fr.riot.ap2.models;

import fr.riot.ap2.entities.Author;
import fr.riot.ap2.entities.Book;
import fr.riot.ap2.entities.Client;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private static List<Author> authors = new ArrayList<>();
    private static List<Book> books = new ArrayList<>();
    private static List<Client> clients = new ArrayList<>();

    public static List<Author> getAuthors() {
        return authors;
    }
    public static List<Book> getBooks() {
        return books;
    }
    public static List<Client> getClients() {
        return clients;
    }

    public static Author getAuthorById(int id) {
        for (Author author : Model.getAuthors())
            if (author.getId() == id)
                return author;

        return null;
    }

    public static Client getClientById(int id) {
        for (Client client : clients)
            if (client.getId() == id)
                return client;

        return null;
    }

    public static Book getBookByISBN(String ISBN) {
        for (Book book : books)
            if (book.getISBN().equalsIgnoreCase(ISBN))
                return book;

        return null;
    }

    public static void load() {
        authors.clear();
        books.clear();
        clients.clear();

        try {
            authors = AuthorModel.getAuthors();
            clients = ClientModel.getClients();
            books = BookModel.getBooks();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
