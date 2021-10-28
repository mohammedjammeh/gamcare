package com.projects.gamcare.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Login {
    @FXML
    private VBox errorBox;

    @FXML
    private Label errorLabel;

    @FXML
    private Label emailFieldLabel;

    @FXML
    private TextField emailTextField;

    @FXML
    private Label passwordFieldLabel;

    @FXML
    private TextField passwordTextField;

    @FXML void initialize() {
        errorBox.setVisible(false);
        errorBox.setManaged(false);
    }

    @FXML
    protected void onLogInButtonClick() {
        errorBox.setVisible(true);
        errorBox.setManaged(true);
        System.out.println("You are now logged in.");
    }
}