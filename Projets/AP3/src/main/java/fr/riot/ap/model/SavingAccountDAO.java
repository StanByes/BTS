package fr.riot.ap.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SavingAccountDAO {
    private static final String GET_ALL = "SELECT `sa`.`id`, `account_id`, `owner_id`, `balance`, `devise`, `interest` FROM `saving_account` AS `sa` INNER JOIN `account` ON `account`.`id` = `sa`.`account_id`";
    private static final String GET_ALL_BY_CLIENT_ID = "SELECT `sa`.`id`, `account_id`, `owner_id`, `balance`, `devise`, `interest` FROM `saving_account` AS `sa` INNER JOIN `account` ON `account`.`id` = `sa`.`account_id` WHERE `owner_id` = ?";
    private static final String INSERT_SAVING_ACCOUNT = "INSERT INTO `saving_account`(`account_id`, `interest`) VALUES (?, ?)";
    private static final String UPDATE_SAVING_ACCOUNT = "UPDATE `saving_account` SET `interest` = ? WHERE `id` = ?";

    public List<SavingAccount> getAll() throws SQLException {
        List<SavingAccount> savingAccounts = new ArrayList<>();

        ClientDAO clientDAO = new ClientDAO();
        try (PreparedStatement preparedStatement = ConnectionFactory.get().prepareStatement(GET_ALL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Client client = clientDAO.getClientById(resultSet.getInt(3));
                    savingAccounts.add(createObject(client, resultSet));
                }
            }
        }

        return savingAccounts;
    }

    public List<SavingAccount> getAllByClient(Client client) throws SQLException {
        List<SavingAccount> savingAccounts = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.get().prepareStatement(GET_ALL_BY_CLIENT_ID)) {
            preparedStatement.setInt(1, client.getId());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    savingAccounts.add(createObject(client, resultSet));
                }
            }
        }

        return savingAccounts;
    }

    public void insertSavingAccount(SavingAccount savingAccount) throws SQLException {
        AccountDAO accountDAO = new AccountDAO();
        Integer accountId = accountDAO.insertAccount(savingAccount);
        if (accountId == null)
            return;

        try (PreparedStatement preparedStatement = ConnectionFactory.get().prepareStatement(INSERT_SAVING_ACCOUNT)) {
            preparedStatement.setInt(1, accountId);
            preparedStatement.setFloat(2, savingAccount.getInterest());

            preparedStatement.execute();
        }
    }

    public void updateSavingAccount(SavingAccount savingAccount) throws SQLException {
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.updateAccount(savingAccount);

        try (PreparedStatement preparedStatement = ConnectionFactory.get().prepareStatement(UPDATE_SAVING_ACCOUNT)) {
            preparedStatement.setFloat(1, savingAccount.getInterest());
            preparedStatement.setInt(2, savingAccount.getId(false));

            preparedStatement.execute();
        }
    }

    private SavingAccount createObject(Client client, ResultSet resultSet) throws SQLException {
        return new SavingAccount(
                resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getFloat(4),
                Account.Devise.valueOf(resultSet.getString(5)),
                client,
                resultSet.getFloat(6));
    }
}
