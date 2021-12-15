package com.projects.gamcare.controllers;

import com.projects.gamcare.core.Hash;
import com.projects.gamcare.fields.LoginFields;
import com.projects.gamcare.models.User;
import javafx.fxml.FXML;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Login extends LoginFields {
    public void initialize() {
        super.initialize();
        emailAddressTextField.setText("mohammedjammeh@yahoo.com");
        passwordTextField.setText("password123");






        String emailAddress = getEmailAddress();
        String password = getPassword();

        Map<String, Object> user = User.where("email_address", emailAddress).first();

        System.out.println(user);

//        byte[] userSalt = user.get("salt");
//        String userHash = user.get("hash");

//        String expectedHash = Hash.generate("password123", userSalt);
//
//        System.out.println(expectedHash);
    }

    @FXML
    protected void onLogInButtonClick() {

    }
}