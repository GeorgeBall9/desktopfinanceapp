package com.georgeBall.controller;

import com.georgeBall.App;
import com.georgeBall.dao.TransactionDao;
import com.georgeBall.model.Transaction;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class DashboardController {
    @FXML
    private Label totalIncomeLabel;
    @FXML
    private Label totalExpenseLabel;
    @FXML
    private Label balanceLabel;
    @FXML
    private ListView<String> recentTransactionsListView;
    @FXML
    private Button addTransactionButton;

    private TransactionDao transactionDao = new TransactionDao();

    public void initialize() {
        addTransactionButton.setOnAction(event -> App.loadTransactionView((Stage) addTransactionButton.getScene().getWindow()));
        loadRecentTransactions();
        // Other initialization logic
    }

    public void loadRecentTransactions() {
        recentTransactionsListView.getItems().clear();
        for (Transaction transaction : transactionDao.getAll()) {
            recentTransactionsListView.getItems().add(transaction.getDescription() + ": £" + transaction.getAmount());
        }
    }
}
