package com.projects.gamcare.controllers.patient;

import com.projects.gamcare.core.SceneTool;
import com.projects.gamcare.models.Patient;
import com.projects.gamcare.models.User;
import javafx.fxml.FXML;

public class Create extends CreateFields {
    @FXML
    protected void onCreatePatientButtonClick() {
        (new Patient()).insert(newPatientData());

        Patient newPatient = getNewPatient();

        (new User()).insert(newUserData(newPatient));

        SceneTool.switchToProfile("patient/show", getAuthUser(), newPatient);
    }

    private Patient getNewPatient() {
        return (Patient) (new Patient())
            .with("users")
            .where("weight", weightInput())
            .where("height", heightInput())
            .where("blood_types_id", bloodTypes.get(bloodTypeIndexInput()).idAttribute())
            .where("hospitals_id", hospitals.get(hospitalIndexInput()).idAttribute())
            .first();
    }
}
