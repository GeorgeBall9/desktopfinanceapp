package com.georgeBall.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.georgeBall.model.User;
import com.georgeBall.service.UserService;
import com.georgeBall.App;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    private UserService userService = new UserService();
    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void handleLoginButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Please fill in all fields");
            return;
        }

        User user = userService.loginUser(username, password);
        if (user != null) {
            // Successful login
            showAlert("Success", "Login successful");
            System.out.println("Login successful, loading dashboard...");
            App.loadDashboard(primaryStage);
        } else {
            // Invalid credentials
            showAlert("Error", "Invalid username or password");
        }
    }

    @FXML
    private void handleRegisterButtonAction() {
        loadRegistration();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    private void loadRegistration() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/registration.fxml"));
            Scene scene = new Scene(loader.load());
            RegistrationController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);
            primaryStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
