package fr.riot.ap2.models;

import fr.riot.ap2.entities.Author;
import fr.riot.ap2.entities.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientModel extends BaseModel {
    private static final String GET_CLIENTS = "SELECT * FROM `clients`";

    public static List<Client> getClients() throws SQLException {
        List<Client> clients = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(GET_CLIENTS)) {
                while (resultSet.next())
                    clients.add(new Client(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDate(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getTimestamp(7)
                    ));
            }
        }

        return clients;
    }
}
