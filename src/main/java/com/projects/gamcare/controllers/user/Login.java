package com.projects.gamcare.controllers.user;

import com.projects.gamcare.core.Controller;
import com.projects.gamcare.core.Hash;
import com.projects.gamcare.core.SceneTool;
import com.projects.gamcare.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Login extends Controller {
    @FXML
    protected TextField emailAddressTextField, passwordTextField;

    @FXML
    protected Button logInButton;

    public void initialize() {
        hideErrorBox();

        emailAddressTextField.setText("mohammedjammeh@yahoo.com");
        passwordTextField.setText("password123");
    }

    @FXML
    protected void onLogInButtonClick() {
        User user = (User) (new User()).where("email_address", emailInput()).first();

        if (hashEqualsExpectedPasswordInputHash(user)) {
            user.switchToAfterLoginResource();
            SceneTool.closeWindow(logInButton);

            return;
        }

        showErrorBox();
    }

    private boolean hashEqualsExpectedPasswordInputHash(User user) {
        if (user == null) {
            return false;
        }

        Object userHash = user.getAttribute("hash");
        String expectedHash = Hash.generate(passwordInput(), user.getSalt());

        return userHash.equals(expectedHash);
    }

    public String emailInput() {
        return emailAddressTextField.getText();
    }

    public String passwordInput() {
        return passwordTextField.getText();
    }
}