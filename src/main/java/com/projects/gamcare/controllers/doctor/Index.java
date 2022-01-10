package com.projects.gamcare.controllers.doctor;

import com.projects.gamcare.core.Controller;
import com.projects.gamcare.models.Doctor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class Index extends Controller {
    @FXML
    protected VBox doctorsTable;

    @FXML
    protected Button addDoctorButton;

    @FXML
    protected void onAddDoctorButtonClick() {
        System.out.println("You have now added a doctor.");
    }

    public void setUpBody() {
        buildDoctorsSection((new Doctor()).with("users").getAll(), doctorsTable);
    }
}
