package com.projects.gamcare.controllers;

import com.projects.gamcare.fields.main.LoginFields;
import javafx.fxml.FXML;

public class Login extends LoginFields {
    @FXML
    protected void onLogInButtonClick() {
        String emailAddress = getEmailAddress();
        String password = getPassword();
    }
}