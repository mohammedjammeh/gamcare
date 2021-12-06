package com.projects.gamcare.controllers.models.hospital;

import javafx.fxml.FXML;

public class Show {
    @FXML
    protected void onEditHospitalButtonClick() {
        System.out.println("You have now edit a hospital.");
    }


    @FXML
    protected void onAddPatientButtonClick() {
        System.out.println("You have now added a patient.");
    }

    @FXML
    protected void onShowPatientButtonClick() {
        System.out.println("You can now see a patient.");
    }


    @FXML
    protected void onAddDoctorButtonClick() {
        System.out.println("You have now added a doctor.");
    }

    @FXML
    protected void onShowDoctorButtonClick() {
        System.out.println("You can now see a doctor.");
    }

}
