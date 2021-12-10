package com.projects.gamcare.fields.main;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginFields extends BaseFields {
    @FXML
    protected TextField emailAddressTextField;

    @FXML
    protected TextField passwordTextField;

    public String getEmailAddress() {
        return emailAddressTextField.getText();
    }

    public String getPassword() {
        return passwordTextField.getText();
    }
}
