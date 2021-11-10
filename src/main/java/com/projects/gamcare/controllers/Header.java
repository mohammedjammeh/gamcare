package com.projects.gamcare.controllers;

import javafx.fxml.FXML;

public class Header {
    @FXML
    protected void onHospitalsButtonClick() {
        System.out.println("You are now looking at hospitals.");
    }

    @FXML
    protected void onMyProfileButtonClick() {
        System.out.println("You are now looking at your profile.");
    }

    @FXML
    protected void onLogOutButtonClick() {
        System.out.println("You are now logged out.");
    }
}