package fr.riot.ap.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String DB = "ap3";
    private static final String USER = "ap3";
    private static final String PWD = "1234";

    public static Connection get() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DB, USER, PWD);
        } catch (ClassNotFoundException | SQLException exception) {
            System.err.println("Une erreur est survenue lors de la connexion à la base de données :");
            throw new RuntimeException(exception);
        }
    }
}
