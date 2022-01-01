package com.projects.gamcare.controllers.hospital;

import com.projects.gamcare.core.Controller;
import javafx.fxml.FXML;

public class Show extends Controller {
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
