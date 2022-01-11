package com.projects.gamcare.controllers.user;

import com.projects.gamcare.core.Hash;
import com.projects.gamcare.models.User;
import javafx.fxml.FXML;

public class Login extends LoginFields {
    public void initialize() {
        super.initialize();
        emailAddressTextField.setText("mohammedjamme@yahoo.com");
        passwordTextField.setText("password123");
    }

    @FXML
    protected void onLogInButtonClick() {
        User user = (new User()).where("email_address", emailInput()).first();

        if (hashEqualsExpectedPasswordInputHash(user))
            user.switchToAfterLoginResource();

        showErrorBox();
    }

    private boolean hashEqualsExpectedPasswordInputHash(User user) {
        if (user == null)
            return false;

        Object userHash = user.getAttribute("hash");
        String expectedHash = Hash.generate(passwordInput(), user.getSalt());

        return userHash.equals(expectedHash);
    }
}