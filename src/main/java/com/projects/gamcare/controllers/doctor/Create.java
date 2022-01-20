package com.projects.gamcare.controllers.doctor;

import javafx.fxml.FXML;
import java.time.LocalDate;
import java.util.List;

public class Create extends CreateFields {
    @FXML
    protected void onAddDoctorsButtonClick() {
        List<Integer> studentDoctorsIndices = getStudentDoctorsIndices();
        List<Integer> juniorDoctorsIndices = getJuniorDoctorsIndices();
        List<Integer> seniorDoctorsIndices = getSeniorDoctorsIndices();
    }


    @FXML
    protected void onCreateDoctorButtonClick() {

    }
}
