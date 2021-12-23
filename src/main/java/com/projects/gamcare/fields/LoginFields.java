package com.projects.gamcare.fields;

import com.projects.gamcare.fields.main.BaseFields;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginFields extends BaseFields {
    @FXML
    protected TextField emailAddressTextField;

    @FXML
    protected TextField passwordTextField;

    @FXML
    protected Button logInButton;

    public String getEmailAddress() {
        return emailAddressTextField.getText();
    }

    public String getPassword() {
        return passwordTextField.getText();
    }
}
