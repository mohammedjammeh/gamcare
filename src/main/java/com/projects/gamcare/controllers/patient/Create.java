package com.projects.gamcare.controllers.patient;

import com.projects.gamcare.controllers.user.CreateParent;
import com.projects.gamcare.core.SceneTool;
import com.projects.gamcare.enums.UserType;
import com.projects.gamcare.models.*;
import com.projects.gamcare.models.main.Model;
import com.projects.gamcare.wrappers.ChoiceBox;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Create extends CreateParent {
    private List<Model> hospitals, bloodTypes;

    @FXML
    protected TextField weightTextField, heightTextField;

    @FXML
    protected ChoiceBox hospitalChoiceBox, bloodTypeChoiceBox;

    public void setUpCreateForm() {
        hospitals = (new Hospital()).getAll();
        bloodTypes = (new BloodType()).getAll();

        hospitalChoiceBox.setItems(getNames(hospitals));
        bloodTypeChoiceBox.setItems(getNames(bloodTypes));

        hospitalChoiceBox.getSelectionModel().select(hospitals.indexOf(getHospital()));
    }

    @FXML
    protected void onCreatePatientButtonClick() {
        (new Patient()).insert(newPatientData());

        Patient newPatient = getNewPatient();

        (new User()).insert(
            newUserData(newPatientUserData(newPatient))
        );

        SceneTool.switchToProfile("patient/show", getAuthUser(), getNewPatientWithUser());
        SceneTool.closeWindow(bloodTypeChoiceBox);
    }

    private Map<String, Object> newPatientData() {
        TreeMap<String, Object> data = new TreeMap<>();

        data.put("weight", weightInput());
        data.put("height", heightInput());
        data.put("blood_types_id", getInputId(bloodTypeChoiceBox, bloodTypes));
        data.put("hospitals_id", getInputId(hospitalChoiceBox, hospitals));

        return data;
    }

    private Map<String, Object> newPatientUserData(Patient patient) {
        TreeMap<String, Object> data = new TreeMap<>();

        data.put("type", UserType.PATIENT.name());
        data.put("patients_id", patient.idAttribute());

        return data;
    }

    private Patient getNewPatient() {
        return (Patient) (new Patient())
            .where("weight", weightInput())
            .where("height", heightInput())
            .where("blood_types_id", getInputId(bloodTypeChoiceBox, bloodTypes))
            .where("hospitals_id", getInputId(hospitalChoiceBox, hospitals))
            .first();
    }

    private Patient getNewPatientWithUser() {
        return (Patient) (new Patient())
            .with("users")
            .where("weight", weightInput())
            .where("height", heightInput())
            .where("blood_types_id", getInputId(bloodTypeChoiceBox, bloodTypes))
            .where("hospitals_id", getInputId(hospitalChoiceBox, hospitals))
            .first();
    }

    private String weightInput() {
        return weightTextField.getText();
    }

    private String heightInput() {
        return heightTextField.getText();
    }
}
