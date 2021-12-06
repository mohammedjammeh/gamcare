package com.projects.gamcare.controllers.index;

import javafx.fxml.FXML;

public class Doctor {
    @FXML
    protected void onAddDoctorButtonClick() {
        System.out.println("You have now added a doctor.");
    }

    @FXML
    protected void onShowDoctorButtonClick() {
        System.out.println("You can now see a doctor.");
    }
}
