package com.georgeBall;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.georgeBall.controller.LoginController;
import com.georgeBall.controller.TransactionController;
import com.georgeBall.dao.DatabaseManager;
import com.georgeBall.model.Transaction;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        DatabaseManager.initializeDatabase();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Scene scene = new Scene(loader.load());

            // Set the primary stage in the LoginController
            LoginController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);

            primaryStage.setTitle("Login");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadDashboard(Stage primaryStage) {
        try {
            System.out.println("Loading dashboard...");
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/fxml/dashboard.fxml"));
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Dashboard");
            System.out.println("Dashboard loaded");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

    public static void loadTransactionView(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/fxml/transaction.fxml"));
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Add Transaction");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
