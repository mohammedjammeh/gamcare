package com.projects.gamcare.controllers.patient;

import com.projects.gamcare.controllers.user.CreateFieldsParent;
import com.projects.gamcare.core.Hash;
import com.projects.gamcare.core.TimeTool;
import com.projects.gamcare.enums.UserType;
import com.projects.gamcare.models.*;
import com.projects.gamcare.models.main.Model;
import com.projects.gamcare.wrappers.ChoiceBox;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CreateFields extends CreateFieldsParent {
    protected List<Model> hospitals, bloodTypes;

    @FXML
    protected TextField weightTextField, heightTextField;

    @FXML
    protected ChoiceBox hospitalChoiceBox, bloodTypeChoiceBox;

    public void initialize() {
        super.initialize();

        hospitals = (new Hospital()).getAll();
        bloodTypes = (new BloodType()).getAll();

        hospitalChoiceBox.setItems(getNames(hospitals));
        bloodTypeChoiceBox.setItems(getNames(bloodTypes));
    }

    protected Map<String, Object> newPatientData() {
        TreeMap<String, Object> data = new TreeMap<>();

        data.put("weight", weightInput());
        data.put("height", heightInput());
        data.put("blood_types_id", bloodTypes.get(bloodTypeIndexInput()).idAttribute());
        data.put("hospitals_id", hospitals.get(hospitalIndexInput()).idAttribute());

        return data;
    }

    public Map<String, Object> newUserData(Patient patient) {
        Map<String, Object> data = super.newUserData();

        data.put("type", UserType.PATIENT.name());
        data.put("patients_id", patient.idAttribute());

        return data;
    }

    public Integer hospitalIndexInput() {
        return hospitalChoiceBox.getSelectionModel().getSelectedIndex();
    }

    public String weightInput() {
        return weightTextField.getText();
    }

    public String heightInput() {
        return heightTextField.getText();
    }

    public Integer bloodTypeIndexInput() {
        return bloodTypeChoiceBox.getSelectionModel().getSelectedIndex();
    }
}
