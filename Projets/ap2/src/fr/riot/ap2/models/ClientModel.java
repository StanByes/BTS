package fr.riot.ap2.models;

import fr.riot.ap2.entities.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientModel extends BaseModel {
    private static final String GET_CLIENTS = "SELECT * FROM `clients`";
    private static final String UPDATE_CLIENT = "UPDATE `clients` SET `firstname` = ?, `surname` = ?, `email` = ?, `phone` = ? WHERE id = ?";

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

    public static void updateClient(Client client) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENT)) {
            preparedStatement.setString(1, client.getFirstName());
            preparedStatement.setString(2, client.getLastName());
            preparedStatement.setString(3, client.getEmail());
            preparedStatement.setString(4, client.getPhone());

            preparedStatement.setInt(5, client.getId());
            preparedStatement.executeUpdate();
        }
    }
}
