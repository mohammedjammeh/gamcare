package com.projects.gamcare.controllers;

import com.projects.gamcare.core.Hash;
import com.projects.gamcare.core.SceneTool;
import com.projects.gamcare.fields.LoginFields;
import com.projects.gamcare.models.User;
import com.projects.gamcare.models.main.Model;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class Login extends LoginFields {
    public void initialize() {
        super.initialize();
        emailAddressTextField.setText("mohammedjammeh@yahoo.com");
        passwordTextField.setText("password123");
    }

    @FXML
    protected void onLogInButtonClick() throws IOException {
        String emailAddress = getEmailAddress();
        String password = getPassword();

        Model user = (new User()).where("email_address", emailAddress).first();

        byte[] userSalt = user.getSalt();
        Object userHash = user.attributes.get("hash");

        String expectedHash = Hash.generate(password, userSalt);

        if (userHash.equals(expectedHash)) {
            SceneTool.switchTo("hospital/index.fxml", logInButton.getScene().getWindow());
        }
    }
}