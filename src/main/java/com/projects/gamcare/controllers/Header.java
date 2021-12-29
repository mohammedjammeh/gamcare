package com.projects.gamcare.controllers;

import com.projects.gamcare.core.SceneTool;
import com.projects.gamcare.enums.UserType;
import com.projects.gamcare.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class Header {
    private User user;

    @FXML
    protected Button hospitalsMenuButton;

    @FXML
    protected Button doctorsMenuButton;

    @FXML
    protected Button myProfileMenuButton;

    @FXML
    protected Button logOutButton;

    public void initialize() {
        hospitalsMenuButton.setVisible(false);
        hospitalsMenuButton.setManaged(false);

        doctorsMenuButton.setVisible(false);
        doctorsMenuButton.setManaged(false);
    }

    @FXML
    protected void onHospitalsMenuButtonClick() throws Exception {
        SceneTool.switchTo(hospitalsMenuButton.getScene().getWindow(), "hospital/index", user);
    }

    @FXML
    protected void onDoctorsMenuButtonClick() throws Exception {
        SceneTool.switchTo(doctorsMenuButton.getScene().getWindow(), "doctor/index", user);
    }

    @FXML
    protected void onMyProfileMenuButtonClick() throws Exception {
        SceneTool.switchTo(myProfileMenuButton.getScene().getWindow(), "patient/show", user);
    }

    @FXML
    protected void onLogOutMenuButtonClick() throws Exception {
        SceneTool.switchToLogin(logOutButton.getScene().getWindow());
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void displayButtons() {
        if (user.isAdmin() || user.isDoctor()) {
            hospitalsMenuButton.setVisible(true);
            hospitalsMenuButton.setManaged(true);
        }

        if (user.isAdmin()) {
            doctorsMenuButton.setVisible(true);
            doctorsMenuButton.setManaged(true);
        }
    }
}