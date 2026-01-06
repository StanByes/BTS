package fr.riot.ap.model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    private static final String GET_ALL = "SELECT `id`, `name`, `firstname`, `genre`, `birthdate`, `category`, `address`, `phone`, `email` FROM `client`";
    private static final String GET_BY_ID = "SELECT `id`, `name`, `firstname`, `genre`, `birthdate`, `category`, `address`, `phone`, `email` FROM `client` WHERE `id` = ?";
    private static final String INSERT_CLIENT = "INSERT INTO `client`(`name`, `firstname`, `genre`, `birthdate`, `category`, `address`, `phone`, `email`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_CLIENT = "UPDATE `client` SET `name` = ?, `firstname` = ?, `genre` = ?, `birthdate` = ?, `category` = ?, `address` = ?, `phone` = ?, `email` = ? WHERE `id` = ?";

    public List<Client> getAll() throws SQLException {
        List<Client> clients = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.get().prepareStatement(GET_ALL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    clients.add(createObject(resultSet));
                }
            }
        }

        return clients;
    }

    public Client getClientById(int id) throws SQLException {
        Client client = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.get().prepareStatement(GET_BY_ID)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next())
                    client = createObject(resultSet);
            }
        }

        return client;
    }

    public void insertClient(String name, String firstname, Client.Genre genre, Date birthdate, String category, String address, String phone, String email) throws SQLException {
        try (PreparedStatement preparedStatement = ConnectionFactory.get().prepareStatement(INSERT_CLIENT)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, firstname);
            preparedStatement.setInt(3, Client.Genre.getByEnumValue(genre));
            preparedStatement.setDate(4, birthdate);
            preparedStatement.setString(5, category);
            preparedStatement.setString(6, address);
            preparedStatement.setString(7, phone);
            preparedStatement.setString(8, email);

            preparedStatement.execute();
        }
    }

    public void updateClient(Client client) throws SQLException {
        try (PreparedStatement preparedStatement = ConnectionFactory.get().prepareStatement(UPDATE_CLIENT)) {
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getFirstname());
            preparedStatement.setInt(3, Client.Genre.getByEnumValue(client.getGenre()));
            preparedStatement.setDate(4, client.getBirthdate());
            preparedStatement.setString(5, client.getCategory());
            preparedStatement.setString(6, client.getAddress());
            preparedStatement.setString(7, client.getPhone());
            preparedStatement.setString(8, client.getEmail());

            preparedStatement.setInt(9, client.getId());
            preparedStatement.executeUpdate();
        }
    }

    private Client createObject(ResultSet resultSet) throws SQLException {
        Client client = new Client(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                Client.Genre.getByDatabaseValue(resultSet.getInt(4)),
                resultSet.getDate(5),
                resultSet.getString(6),
                resultSet.getString(7),
                resultSet.getString(8),
                resultSet.getString(9)
        );

        client.getAccounts().addAll(new CurrentAccountDAO().getAllByClient(client));
        client.getAccounts().addAll(new SavingAccountDAO().getAllByClient(client));

        return client;
    }
}
