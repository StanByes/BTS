package fr.riot.ap2.connectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnection {
    private final String driver;
    private final String host;
    private final int port;
    private final String username;
    private final String password;
    private final String database;

    private Connection currentConnection;

    public MysqlConnection(String driver, String host, int port, String username, String password, String database) {
        this.driver = driver;
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.database = database;
    }

    public Connection getConnection() throws SQLException {
        String url = "jdbc:" + driver + "://" + host + ":" + port + "/" + database;
        currentConnection = DriverManager.getConnection(url, username, password);
        return currentConnection;
    }

    public void releaseConnection() throws SQLException {
        if (currentConnection == null)
            return;

        currentConnection.close();
        currentConnection = null;
    }
}
