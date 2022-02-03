package com.projects.gamcare.controllers.patient;

import com.projects.gamcare.controllers.patient.parents.Fields;
import com.projects.gamcare.core.SceneTool;
import com.projects.gamcare.models.BloodType;
import com.projects.gamcare.models.Hospital;
import com.projects.gamcare.models.Patient;
import com.projects.gamcare.models.main.ProfileUser;
import javafx.fxml.FXML;

public class Edit extends Fields {
    private ProfileUser profileUser;

    public void setUpEditForm() {
        hospitals = (new Hospital()).getAll();
        bloodTypes = (new BloodType()).getAll();

        hospitalChoiceBox.setItems(getNames(hospitals));
        bloodTypeChoiceBox.setItems(getNames(bloodTypes));

        setInputData(profileUser);
    }

    @FXML
    protected void onUpdatePatientButtonClick() {
        profileUser.getPatient().update(getInputData());

        Patient newPatient = getPatientFromDB();

        profileUser.getPatientUser().update(newUserData(
            newPatientUserData(newPatient))
        );

        SceneTool.switchToProfile("patient", getAuthUser(), getPatientWithUserFromDB());
        SceneTool.closeWindow(bloodTypeChoiceBox);
    }

    /**
     * Getters & Setters
     */
    public void setProfileUser(ProfileUser profileUser) {
        this.profileUser = profileUser;
    }
}
