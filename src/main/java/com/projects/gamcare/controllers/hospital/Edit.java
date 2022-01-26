package com.projects.gamcare.controllers.hospital;

import com.projects.gamcare.controllers.hospital.parents.Fields;
import javafx.fxml.FXML;

public class Edit extends Fields {
    @FXML
    protected void onUpdateHospitalButtonClick() {
        System.out.println(getInputData());
    }
}
