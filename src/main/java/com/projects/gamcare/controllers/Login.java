package com.projects.gamcare.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Login {

    @FXML
    private Label title;

    @FXML
    private Label usernameLabel;

    @FXML
    private TextField usernameText;

    @FXML
    private Label passwordLabel;

    @FXML
    private TextField passwordText;

    @FXML
    protected void onLoginButtonClick() {
        System.out.println("You are now logged in.");
    }
}