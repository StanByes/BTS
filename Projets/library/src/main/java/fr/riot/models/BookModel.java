package fr.riot.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.riot.Main;
import fr.riot.classes.Book;

public class BookModel {
    private static final String GET_ALL_BOOKS = "SELECT `id`, `name`, `author`, `total`, `published_at` FROM `books`";
    private static final String CREATE_BOOK = "INSERT INTO `books`(`name`, `author`, `total`, `published_at`) VALUES (?, ?, ?, ?)";

    public static List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();

        try (PreparedStatement preparedStatement = Main.getConnection().getConnection().prepareStatement(GET_ALL_BOOKS)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next())
                    books.add(new Book(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getDate(5)));
            }
        } finally {
            Main.getConnection().releaseConnection();
        }

        return books;
    }

    public static int createBook(Book book) throws SQLException {
    	try (PreparedStatement preparedStatement = Main.getConnection().getConnection().prepareStatement(CREATE_BOOK, Statement.RETURN_GENERATED_KEYS)) {
    		preparedStatement.setString(1, book.getName());
    		preparedStatement.setString(2, book.getAuthor());
    		preparedStatement.setLong(3, book.getTotal());
    		preparedStatement.setDate(4, book.getPublishedAt());
    		
    		preparedStatement.executeUpdate();
    		try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
    			if (resultSet.next())
    				return resultSet.getInt(1);

				return -1;
    		}
    	}
    }
}
