package fr.riot.ap2.models;

import fr.riot.ap2.entities.Author;
import fr.riot.ap2.entities.Book;
import fr.riot.ap2.entities.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookModel extends BaseModel {
    private static final String GET_BOOKS = "SELECT * FROM `books`";

    public static List<Book> getBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(GET_BOOKS)) {
                while (resultSet.next()) {
                    Author author = null;
                    for (Author searchAuthor : Model.getAuthors()) {
                        if (searchAuthor.getId() == resultSet.getInt(4)) {
                            author = searchAuthor;
                            break;
                        }
                    }

                    if (author == null) {
                        System.err.println("Book " + resultSet.getInt(1) + " has an unknown author");
                        continue;
                    }

                    Client client = null;
                    for (Client searchClient : Model.getClients()) {
                        if (searchClient.getId() == resultSet.getInt(5)) {
                            client = searchClient;
                            break;
                        }
                    }

                    books.add(new Book(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            author,
                            client
                    ));
                }
            }
        }

        return books;
    }
}
