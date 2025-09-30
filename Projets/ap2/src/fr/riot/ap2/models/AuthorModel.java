package fr.riot.ap2.models;

import fr.riot.ap2.entities.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AuthorModel extends BaseModel {
    private static final String GET_AUTHORS = "SELECT * FROM `authors`";

    public static List<Author> getAuthors() throws SQLException {
        List<Author> authors = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(GET_AUTHORS)) {
                while (resultSet.next())
                    authors.add(new Author(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getDate(5)
                    ));
            }
        }

        return authors;
    }
}
