package fr.riot.ap.model;

import java.sql.*;

public class AccountDAO {
    private static final String INSERT_ACCOUNT = "INSERT INTO `account`(`owner_id`, `balance`, `devise`) VALUES (?, ?, ?)";
    private static final String UPDATE_ACCOUNT = "UPDATE `account` SET `balance` = ?, `devise` = ? WHERE `id` = ?";

    public Integer insertAccount(Account account) throws SQLException {
        try (PreparedStatement preparedStatement = ConnectionFactory.get().prepareStatement(INSERT_ACCOUNT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, account.getOwner().getId());
            preparedStatement.setFloat(2, account.getBalance());
            preparedStatement.setString(3, account.getDevise().name());

            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next())
                    return resultSet.getInt(1);
            }
        }

        return null;
    }

    public void updateAccount(Account account) throws SQLException {
        try (PreparedStatement preparedStatement = ConnectionFactory.get().prepareStatement(UPDATE_ACCOUNT)) {
            preparedStatement.setFloat(1, account.getBalance());
            preparedStatement.setString(2, account.getDevise().name());
            preparedStatement.setInt(3, account.getId(true));

            preparedStatement.execute();
        }
    }
}
