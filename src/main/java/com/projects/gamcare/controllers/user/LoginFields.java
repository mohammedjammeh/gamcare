package com.projects.gamcare.controllers.user;

import com.projects.gamcare.core.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginFields extends Controller {
    @FXML
    protected TextField emailAddressTextField;

    @FXML
    protected TextField passwordTextField;

    @FXML
    protected Button logInButton;

    public void initialize() {
        hideErrorBox();
    }

    public String emailInput() {
        return emailAddressTextField.getText();
    }

    public String passwordInput() {
        return passwordTextField.getText();
    }
}
