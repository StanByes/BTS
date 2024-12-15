package fr.riot.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.riot.Main;
import fr.riot.classes.Client;

public class ClientModel {
    public static final String GET_ALL_CLIENTS = "SELECT `id`, `firstname`, `surname`, `birthday`, `email`, `phone`, `registered_at` FROM `clients`";
    public static final String CREATE_CLIENT = "INSERT INTO `clients`(`firstname`, `surname`, `birthday`, `email`, `phone`, `registered_at`) VALUES (?, ?, ?, ?, ?, NOW())";

    public static List<Client> getAllClients() throws SQLException {
        List<Client> clients = new ArrayList<>();

        try (PreparedStatement preparedStatement = Main.getConnection().getConnection().prepareStatement(GET_ALL_CLIENTS)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next())
                    clients.add(new Client(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4), resultSet.getString(5), resultSet.getString(6), resultSet.getDate(7)));
            }
        } finally {
            Main.getConnection().releaseConnection();
        }

        return clients;
    }
    
    public static int createClient(Client client) throws SQLException {
    	try (PreparedStatement preparedStatement = Main.getConnection().getConnection().prepareStatement(CREATE_CLIENT, Statement.RETURN_GENERATED_KEYS)) {
    		preparedStatement.setString(1, client.getFirstName());
    		preparedStatement.setString(2, client.getSurName());
    		preparedStatement.setDate(3, client.getBirthDay());
    		preparedStatement.setString(4, client.getEmail());
    		preparedStatement.setString(5, client.getPhoneNumber());
    		
    		preparedStatement.executeUpdate();
    		try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
    			if (resultSet.next())
    				return resultSet.getInt(1);
    			
    			return -1;
    		}
    	} finally {
    		Main.getConnection().releaseConnection();
    	}
    }
}
