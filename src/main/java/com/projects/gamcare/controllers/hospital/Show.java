package com.projects.gamcare.controllers.hospital;

import com.projects.gamcare.controllers.Main;
import javafx.fxml.FXML;

public class Show extends Main {
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
