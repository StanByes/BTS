package fr.riot.ap.controller;

import fr.riot.ap.App;
import fr.riot.ap.controls.ExpirationYearMonthPicker;
import fr.riot.ap.model.*;
import fr.riot.ap.utils.ChoosableEnumFormatter;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.sql.SQLException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class AccountIndexController {
    @FXML
    private ListView<Client> clientList;
    @FXML
    private ListView<Account> accountList;

    private ToggleGroup typeGroup;
    @FXML
    private RadioButton current;
    @FXML
    private RadioButton saving;

    @FXML
    private ChoiceBox<Account.Devise> deviseChoiceBox;

    @FXML
    private TextField balance;

    @FXML
    private Pane currentAccountPane;
    @FXML
    private TextField cardNumber;
    @FXML
    private ExpirationYearMonthPicker expiration;
    @FXML
    private TextField cvv;
    @FXML
    private TextField overdraft;

    @FXML
    private Pane savingAccountPane;
    @FXML
    private TextField interest;

    @FXML
    private Button submit;

    private Client selectedClient = null;
    private Account selectedAccount = null;

    enum AccountType {
        CURRENT, SAVING
    };
    private final List<Control> controls = new ArrayList<>();
    private AccountType accountType;

    public void initialize() {
        // List all control fields
        controls.add(current);
        controls.add(saving);
        controls.add(deviseChoiceBox);
        controls.add(balance);
        controls.add(submit);

        // Disable all controls by default
        setControlsState(false);

        // Load client list
        clientList.setCellFactory(param -> new ListCell<>() {
            @Override
            public void updateItem(Client client, boolean empty) {
                super.updateItem(client, empty);

                setText(empty || client == null ? "" : client.getCompleteName());
            }
        });

        try {
            ClientDAO clientDAO = new ClientDAO();
            List<Client> clients = clientDAO.getAll();
            clientList.getItems().addAll(clients);
        } catch (SQLException exception) {
            // Throw an error !
            return;
        }
        
        // Define cell factory for accountView
        accountList.setCellFactory(param -> new ListCell<>() {
            @Override
            public void updateItem(Account account, boolean empty) {
                super.updateItem(account, empty);

                setText(empty || account == null ? "" : account.formatAccount());
            }
        });

        // Handle list choice change
        clientList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedClient = newValue;

            accountList.getItems().clear();
            accountList.getItems().addAll(selectedClient.getAccounts());

            if (accountType != null)
                resetSelection();
        });

        // Handle account list choice change
        accountList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null)
                return;

            selectedAccount = newValue;

            deviseChoiceBox.setValue(selectedAccount.getDevise());
            balance.setText(String.valueOf(selectedAccount.getBalance()));

            if (selectedAccount instanceof CurrentAccount currentAccount) {
                typeGroup.selectToggle(current);

                cardNumber.setText(String.valueOf(currentAccount.getCardNumber()));
                expiration.setValue(YearMonth.from(currentAccount.getExpiration().toLocalDate()));
                cvv.setText(String.valueOf(currentAccount.getCVV()));
                overdraft.setText(String.valueOf(currentAccount.getOverdraft()));

                accountType = AccountType.CURRENT;
                showTypePane();
            } else if (selectedAccount instanceof SavingAccount savingAccount) {
                typeGroup.selectToggle(saving);

                interest.setText(String.valueOf(savingAccount.getInterest()));

                accountType = AccountType.SAVING;
                showTypePane();

            }

            setControlsState(true);
        });

        // Init group of account types
        typeGroup = new ToggleGroup();
        current.setToggleGroup(typeGroup);
        saving.setToggleGroup(typeGroup);

        // Handle account type choice to change fields view
        typeGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null)
                return;

            if (newValue.equals(current)) {
                accountType = AccountType.CURRENT;
            } else {
                accountType = AccountType.SAVING;
            }

            showTypePane();
        });

        // Set devise list and formatter
        deviseChoiceBox.setConverter(new ChoosableEnumFormatter<>(Account.Devise.class));
        deviseChoiceBox.getItems().addAll(Account.Devise.values());
    }

    @FXML
    public void submitForm() {
        Account account = selectedAccount;

        Account.Devise devise = deviseChoiceBox.getValue();
        String balanceValue = balance.getText();
        if (devise == null || isEmpty(balanceValue)) {
            // Throw an error !
            return;
        }

        float parsedBalance;
        try {
            parsedBalance = Float.parseFloat(balanceValue.replaceAll(",", "."));
        } catch (NumberFormatException exception) {
            // Throw an error !
            return;
        }

        account.setBalance(parsedBalance);
        account.setDevise(devise);

        if (accountType == null) {
            // Throw an error !
        } else if (accountType == AccountType.CURRENT) {
            String cardNumberValue = cardNumber.getText();
            YearMonth expirationValue = expiration.getValue();
            String cvvValue = cvv.getText();
            String overdraftValue = overdraft.getText();

            if (isEmpty(cardNumberValue) || expirationValue == null || isEmpty(cvvValue) || isEmpty(overdraftValue)) {
                // Throw an error !
                return;
            }

            int parsedCardNumber;
            int parsedCvv;
            int parsedOverdraft;
            try {
                parsedCardNumber = Integer.parseInt(cardNumberValue.replaceAll(" ", ""));
                parsedCvv = Integer.parseInt(cvvValue);
                parsedOverdraft = Integer.parseInt(overdraftValue);
            } catch (NumberFormatException exception) {
                // Throw an error !
                return;
            }

            CurrentAccount currentAccount = (CurrentAccount) account;
            currentAccount.setCardNumber(parsedCardNumber);
            currentAccount.setExpiration(expirationValue);
            currentAccount.setCVV(parsedCvv);
            currentAccount.setOverdraft(parsedOverdraft);
            try {
                CurrentAccountDAO currentAccountDAO = new CurrentAccountDAO();
                currentAccountDAO.updateCurrentAccount(currentAccount);
            } catch (SQLException exception) {
                // Throw an error
                throw new RuntimeException(exception);
            }

            // Add flash success
            back();
        } else {
            String interestValue = interest.getText();
            if (isEmpty(interestValue)) {
                // Throw an error !
                return;
            }

            float parsedInterest;
            try {
                parsedInterest = Float.parseFloat(interestValue);
            } catch (NumberFormatException exception) {
                // Throw an error !
                return;
            }

            SavingAccount savingAccount = (SavingAccount) account;
            savingAccount.setInterest(parsedInterest);
            try {
                SavingAccountDAO savingAccountDAO = new SavingAccountDAO();
                savingAccountDAO.updateSavingAccount(savingAccount);
            } catch (SQLException exception) {
                // Throw an error !
                throw new RuntimeException(exception);
            }

            // Add flash success
            back();
        }
    }

    @FXML
    public void back() {
        App.setRoot("home");
    }

    private void setControlsState(boolean isActive) {
        for (Control control : controls)
            control.setDisable(!isActive);
    }

    private void showTypePane() {
        boolean isSaving = accountType == AccountType.SAVING;
        currentAccountPane.setVisible(!isSaving);
        savingAccountPane.setVisible(isSaving);
    }

    private void resetSelection() {
        accountType = null;
        selectedAccount = null;
        setControlsState(false);

        deviseChoiceBox.setValue(null);
        balance.setText(null);

        typeGroup.selectToggle(null);

        cardNumber.setText(null);
        expiration.setValue(null);
        cvv.setText(null);
        overdraft.setText(null);

        interest.setText(null);

        currentAccountPane.setVisible(false);
        savingAccountPane.setVisible(false);
    }

    private boolean isEmpty(String v) {
        return v == null || v.isBlank();
    }
}
