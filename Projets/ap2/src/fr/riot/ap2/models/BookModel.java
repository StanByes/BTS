package fr.riot.ap2.models;

import fr.riot.ap2.entities.Author;
import fr.riot.ap2.entities.Book;
import fr.riot.ap2.entities.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookModel extends BaseModel {
    private static final String GET_BOOKS = "SELECT `id`, `name`, `isbn`, `author_id`, `client_id`, `published_at` FROM `books`";
    private static final String UPDATE_BOOK_CLIENT = "UPDATE `books` SET `client_id` = ? WHERE `id` = ?";

    public static List<Book> getBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(GET_BOOKS)) {
                while (resultSet.next()) {
                    Author author = Model.getAuthorById(resultSet.getInt(4));
                    if (author == null) {
                        System.err.println("Book " + resultSet.getInt(1) + " has an unknown author");
                        continue;
                    }

                    Client client = Model.getClientById(resultSet.getInt(5));
                    Book book = new Book(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            author,
                            client,
                            resultSet.getDate(6)
                    );
                    books.add(book);

                    if (client != null)
                        client.getBooks().add(book);
                }
            }
        }

        return books;
    }

    public static boolean updateBookClient(Book book) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK_CLIENT)) {
            preparedStatement.setInt(1, book.getClient().getId());
            preparedStatement.setInt(2, book.getId());

            return preparedStatement.executeUpdate() == 1;
        }
    }
}
