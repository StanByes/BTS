package fr.riot.ap2.models;

import fr.riot.ap2.connectors.MysqlConnection;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class BaseModel {
    private static final MysqlConnection connector =
            new MysqlConnection("mysql", "localhost", 3306, "root", "1234", "library");
    protected static final Connection connection;

    static {
        try {
            connection = connector.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
