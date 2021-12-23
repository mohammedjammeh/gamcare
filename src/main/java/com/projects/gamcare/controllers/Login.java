package com.projects.gamcare.controllers;

import com.projects.gamcare.fields.LoginFields;
import com.projects.gamcare.models.User;
import com.projects.gamcare.models.main.Model;
import javafx.fxml.FXML;

public class Login extends LoginFields {
    public void initialize() {
        super.initialize();
        emailAddressTextField.setText("mohammedjammeh@yahoo.com");
        passwordTextField.setText("password123");






        String emailAddress = getEmailAddress();
        String password = getPassword();

        Model user = (new User()).where("middle_name", "Musa").first();

        System.out.println(user.getFullName());

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