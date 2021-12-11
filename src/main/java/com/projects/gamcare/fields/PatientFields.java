package com.projects.gamcare.fields;

import com.projects.gamcare.fields.main.UserFields;
import com.projects.gamcare.wrappers.ChoiceBox;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PatientFields extends UserFields {
    @FXML
    protected ChoiceBox hospitalChoiceBox;

    @FXML
    protected TextField weightTextField;

    @FXML
    protected TextField heightTextField;

    @FXML
    protected ChoiceBox bloodTypeChoiceBox;

    public Integer getHospitalIndex() {
        return hospitalChoiceBox.getSelectionModel().getSelectedIndex();
    }

    public String getWeight() {
        return weightTextField.getText();
    }

    public String getHeight() {
        return heightTextField.getText();
    }

    public Integer getBloodTypeIndex() {
        return bloodTypeChoiceBox.getSelectionModel().getSelectedIndex();
    }

    public void initialize() {
        super.initialize();

        hospitalChoiceBox.setItems(getDatabaseItems("hospitals"));
        bloodTypeChoiceBox.setItems(getDatabaseItems("blood_types"));
    }
}
