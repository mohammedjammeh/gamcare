package com.projects.gamcare.controllers.patient;

import com.projects.gamcare.controllers.user.CreateFieldsParent;
import com.projects.gamcare.models.BloodType;
import com.projects.gamcare.models.Hospital;
import com.projects.gamcare.models.Speciality;
import com.projects.gamcare.models.main.Model;
import com.projects.gamcare.wrappers.ChoiceBox;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.List;

public class CreateFields extends CreateFieldsParent {
    @FXML
    protected TextField weightTextField, heightTextField;

    @FXML
    protected ChoiceBox hospitalChoiceBox, bloodTypeChoiceBox;

    protected List<Model> hospitals, bloodTypes;

    public void initialize() {
        super.initialize();

        hospitals = (new Hospital()).getAll();
        bloodTypes = (new BloodType()).getAll();

        hospitalChoiceBox.setItems(getNames(hospitals));
        bloodTypeChoiceBox.setItems(getNames(bloodTypes));
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
