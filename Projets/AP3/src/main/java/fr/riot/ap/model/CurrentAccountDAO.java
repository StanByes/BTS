package fr.riot.ap.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class CurrentAccountDAO {
    private static final String GET_ALL = "SELECT `account`.`id`, `account_id`, `owner_id`, `balance`, `devise`, `card_number`, `expiration`, `cvv`, `overdraft` FROM `current_account` AS `ca` INNER JOIN `account` ON `account`.`id` = `ca`.`account_id`";
    private static final String GET_ALL_BY_CLIENT_ID = "SELECT `ca`.`id`, `account_id`, `owner_id`, `balance`, `devise`, `card_number`, `expiration`, `cvv`, `overdraft` FROM `current_account` AS `ca` INNER JOIN `account` ON `account`.`id` = `ca`.`account_id` WHERE `owner_id` = ?";
    private static final String INSERT_CURRENT_ACCOUNT = "INSERT INTO `current_account`(`account_id`, `card_number`, `expiration`, `cvv`, `overdraft`) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_CURRENT_ACCOUNT = "UPDATE `current_account` SET `card_number` = ?, `expiration` = ?, `cvv` = ?, `overdraft` = ? WHERE `id` = ?";

    public List<CurrentAccount> getAll() throws SQLException {
        List<CurrentAccount> currentAccounts = new ArrayList<>();

        ClientDAO clientDAO = new ClientDAO();
        try (PreparedStatement preparedStatement = ConnectionFactory.get().prepareStatement(GET_ALL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Client client = clientDAO.getClientById(resultSet.getInt(3));
                    currentAccounts.add(createObject(client, resultSet));
                }
            }
        }

        return currentAccounts;
    }

    public List<CurrentAccount> getAllByClient(Client client) throws SQLException {
        List<CurrentAccount> currentAccounts = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.get().prepareStatement(GET_ALL_BY_CLIENT_ID)) {
            preparedStatement.setInt(1, client.getId());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    currentAccounts.add(createObject(client, resultSet));
                }
            }
        }

        return currentAccounts;
    }

    public void insertCurrentAccount(CurrentAccount currentAccount) throws SQLException {
        AccountDAO accountDAO = new AccountDAO();
        Integer accountId = accountDAO.insertAccount(currentAccount);
        if (accountId == null)
            return;

        try (PreparedStatement preparedStatement = ConnectionFactory.get().prepareStatement(INSERT_CURRENT_ACCOUNT)) {
            preparedStatement.setInt(1, accountId);
            preparedStatement.setInt(2, currentAccount.getCardNumber());
            preparedStatement.setDate(3, currentAccount.getExpiration());
            preparedStatement.setInt(4, currentAccount.getCVV());
            preparedStatement.setInt(5, currentAccount.getOverdraft());
            
            preparedStatement.execute();
        }
    }

    public void updateCurrentAccount(CurrentAccount currentAccount) throws SQLException {
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.updateAccount(currentAccount);

        try (PreparedStatement preparedStatement = ConnectionFactory.get().prepareStatement(UPDATE_CURRENT_ACCOUNT)) {
            preparedStatement.setInt(1, currentAccount.getCardNumber());
            preparedStatement.setDate(2, currentAccount.getExpiration());
            preparedStatement.setInt(3, currentAccount.getCVV());
            preparedStatement.setInt(4, currentAccount.getOverdraft());
            preparedStatement.setInt(5, currentAccount.getId(false));

            preparedStatement.execute();
        }
    }

    private CurrentAccount createObject(Client client, ResultSet resultSet) throws SQLException {
        return new CurrentAccount(
                resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getFloat(4),
                Account.Devise.valueOf(resultSet.getString(5)),
                client,
                resultSet.getInt(6),
                resultSet.getDate(7),
                resultSet.getInt(8),
                resultSet.getInt(9));
    }
}
