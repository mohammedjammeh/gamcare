package com.projects.gamcare.controllers.patient;

import com.projects.gamcare.controllers.patient.parents.Fields;
import com.projects.gamcare.core.SceneTool;
import com.projects.gamcare.models.*;
import javafx.fxml.FXML;

public class Create extends Fields {
    private Hospital hospital;

    public void setUpCreateForm() {
        hospitals = (new Hospital()).getAll();
        bloodTypes = (new BloodType()).getAll();

        hospitalChoiceBox.setItems(getNames(hospitals));
        bloodTypeChoiceBox.setItems(getNames(bloodTypes));

        hospitalChoiceBox.getSelectionModel().select(hospitals.indexOf(hospital));
    }

    @FXML
    protected void onCreatePatientButtonClick() {
        (new Patient()).insert(getInputData());

        Patient newPatient = getPatientFromDB();

        (new User()).insert(newUserData(
            newPatientUserData(newPatient))
        );

        SceneTool.switchToProfile("patient", getAuthUser(), getPatientWithUserFromDB());
        SceneTool.closeWindow(bloodTypeChoiceBox);
    }


    /**
     * Getters & Setters
     */
    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
}
