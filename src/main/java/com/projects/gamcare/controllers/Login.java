package com.projects.gamcare.controllers;

import com.projects.gamcare.fields.LoginFields;
import com.projects.gamcare.models.User;
import javafx.fxml.FXML;

public class Login extends LoginFields {
    public void initialize() {
        super.initialize();
        emailAddressTextField.setText("mohammedjammeh@yahoo.com");
        passwordTextField.setText("password123");






        String emailAddress = getEmailAddress();
        String password = getPassword();

        User user = (new User()).where("middle_name", "Musa").last();

//        System.out.println(user);

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