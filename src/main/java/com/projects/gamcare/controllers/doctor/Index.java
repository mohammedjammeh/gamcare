package com.projects.gamcare.controllers.doctor;

import com.projects.gamcare.controllers.Main;
import javafx.fxml.FXML;

public class Index extends Main {
    @FXML
    protected void onAddDoctorButtonClick() {
        System.out.println("You have now added a doctor.");
    }

    @FXML
    protected void onShowDoctorButtonClick() {
        System.out.println("You can now see a doctor.");
    }
}
