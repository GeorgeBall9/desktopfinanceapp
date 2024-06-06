package com.georgeBall.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import com.georgeBall.App;
import com.georgeBall.dao.TransactionDao;
import com.georgeBall.model.Transaction;
import com.georgeBall.service.TransactionService;

public class TransactionController {
    @FXML
    private ListView<String> transactionListView;
    @FXML
    private VBox addTransactionPane;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField amountField;
    @FXML
    private TextField categoryField;
    @FXML
    private TextField dateField;

    private TransactionDao transactionDao = new TransactionDao();
    private TransactionService transactionService = new TransactionService();

    public void initialize() {
        // Load transactions from database
        loadTransactions();
    }

    private void loadTransactions() {
        transactionListView.getItems().clear();
        for (Transaction transaction : transactionDao.getAll()) {
            transactionListView.getItems().add(transaction.getDescription() + ": £" + transaction.getAmount());
        }
    }

    @FXML
    private void handleAddTransactionButtonAction() {
        addTransactionPane.setVisible(true);
    }

    @FXML
    private void handleSaveTransactionButtonAction() {
        String description = descriptionField.getText();
        String amount = amountField.getText();
        String category = categoryField.getText();
        String date = dateField.getText();

        if (description.isEmpty() || amount.isEmpty() || category.isEmpty() || date.isEmpty()) {
            showAlert("Error", "Please fill in all fields");
            return;
        }

        double amountValue;
        try {
            amountValue = Double.parseDouble(amount);
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid amount");
            return;
        }

        Transaction transaction = new Transaction(0, description, amountValue, category, date);
        transactionService.addTransaction(transaction);
        showAlert("Success", "Transaction added successfully");
        loadTransactions();
        clearFields();
    }

    private void clearFields() {
        descriptionField.clear();
        amountField.clear();
        categoryField.clear();
        dateField.clear();
        addTransactionPane.setVisible(false);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleHomeButtonAction() {
        Stage stage = (Stage) transactionListView.getScene().getWindow();
        App.loadDashboard(stage);
    }
}
