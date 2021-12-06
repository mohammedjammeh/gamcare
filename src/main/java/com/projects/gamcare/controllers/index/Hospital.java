package com.projects.gamcare.controllers.index;

import javafx.fxml.FXML;

public class Hospital {
    @FXML
    protected void onAddHospitalButtonClick() {
        System.out.println("You have now added a hospital.");
    }

    @FXML
    protected void onShowHospitalButtonClick() {
        System.out.println("You can now see a hospital.");
    }
}
