package com.projects.gamcare.controllers;

import com.projects.gamcare.core.Hash;
import com.projects.gamcare.fields.LoginFields;
import com.projects.gamcare.models.User;
import javafx.fxml.FXML;

import java.util.Arrays;

public class Login extends LoginFields {
    public void initialize() {
        super.initialize();
        emailAddressTextField.setText("mohammedjammeh@yahoo.com");
        passwordTextField.setText("password123");






        String emailAddress = getEmailAddress();
        String password = getPassword();



        System.out.println(User.where("email_address", emailAddress).first());


        byte[] salt = Hash.createSalt();
        String hash = Hash.generate("password123", salt);
    }

    @FXML
    protected void onLogInButtonClick() {

    }
}