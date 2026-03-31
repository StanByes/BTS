package fr.riot.ap.model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AgentDAO {
    private static final String GET_BY_USERNAME = "SELECT `id`, `login`, `password` FROM `agents` WHERE `login` = ?";
    private static final String GET_WITHOUT_PASSWORD = "SELECT `id`, `login`, `password` FROM `agents` WHERE `password` IS NULL";
    private static final String UPDATE_PASSWORD = "UPDATE `agents` SET `password` = ? WHERE `id` = ?";

    public Agent getAgentByUsername(String username) throws SQLException {
        try (PreparedStatement preparedStatement = ConnectionFactory.get().prepareStatement(GET_BY_USERNAME)) {
            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Agent(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3)
                    );
                }
            }
        }

        return null;
    }

    public List<Agent> getAgentsWithoutPassword() throws SQLException {
        List<Agent> agents = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConnectionFactory.get().prepareStatement(GET_WITHOUT_PASSWORD)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    agents.add(new Agent(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3)
                    ));
                }
            }
        }

        return agents;
    }

    public void updateAgent(Agent agent) throws SQLException {
        try (PreparedStatement preparedStatement = ConnectionFactory.get().prepareStatement(UPDATE_PASSWORD)) {
            preparedStatement.setString(1, agent.getPassword());

            preparedStatement.setInt(2, agent.getId());
            preparedStatement.executeUpdate();
        }
    }

    public void generatePasswordForAgentsWithoutPassword() throws SQLException {
        List<Agent> agentsWithoutPassword = getAgentsWithoutPassword();
        StringBuilder result = new StringBuilder("login;password");

        for (Agent agent : agentsWithoutPassword) {
            String password = Agent.generatePassword();
            result.append("\n").append(agent.getLogin()).append(";").append(password);
            agent.setPassword(password);

            updateAgent(agent);
        }

        try {
            Files.write(Paths.get("./generated_password.csv"), result.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
