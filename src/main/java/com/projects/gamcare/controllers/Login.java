package com.projects.gamcare.controllers;

import com.projects.gamcare.core.Hash;
import com.projects.gamcare.core.SceneTool;
import com.projects.gamcare.fields.LoginFields;
import com.projects.gamcare.models.User;
import javafx.fxml.FXML;

public class Login extends LoginFields {
    public void initialize() {
        super.initialize();
        emailAddressTextField.setText("mohammedjammeh@yahoo.com");
        passwordTextField.setText("password123");
    }

    @FXML
    protected void onLogInButtonClick() {
        User user = (new User()).where("email_address", getEmailAddress()).first();

        byte[] userSalt = user.getSalt();
        Object userHash = user.attributes.get("hash");

        String expectedHash = Hash.generate(getPassword(), userSalt);

        if (!userHash.equals(expectedHash)) {
            // throw exception, etc..
            return;
        }

        if (user.isPatient()) {
            SceneTool.switchToProfile(user.afterLoginResourceName(), user, user);
            return;
        }

        SceneTool.switchTo(user.afterLoginResourceName(), user);
    }
}