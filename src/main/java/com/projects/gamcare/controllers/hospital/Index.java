package com.projects.gamcare.controllers.hospital;

import com.projects.gamcare.core.Controller;
import javafx.fxml.FXML;

public class Index extends Controller {
    @FXML
    protected void onAddHospitalButtonClick() {
        System.out.println("You have now added a hospital.");
    }

    @FXML
    protected void onShowHospitalButtonClick() {
        System.out.println("You can now see a hospital.");
    }
}
