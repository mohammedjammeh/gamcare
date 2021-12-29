package com.projects.gamcare.controllers.doctor;

import com.projects.gamcare.core.Controller;
import javafx.fxml.FXML;

public class Show extends Controller {
    @FXML
    protected void onEditDoctorButtonClick() {
        System.out.println("You have now edit a hospital.");
    }

    @FXML
    protected void onShowDoctorButtonClick() {
        System.out.println("You can now see a hospital.");
    }
}
