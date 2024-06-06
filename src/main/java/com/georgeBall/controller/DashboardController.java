package com.georgeBall.controller;

import com.georgeBall.App;

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

    public void initialize() {
        addTransactionButton
                .setOnAction(event -> App.loadTransactionView((Stage) addTransactionButton.getScene().getWindow()));
        // Other initialization logic
    }
}
