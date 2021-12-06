package com.projects.gamcare.fields;

import com.projects.gamcare.fields.main.UserFields;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class PatientFields extends UserFields {
    @FXML
    protected ChoiceBox<String> hospitalChoiceBox;

    @FXML
    protected TextField weightTextField;

    @FXML
    protected TextField heightTextField;

    @FXML
    protected ChoiceBox<String> bloodTypeChoiceBox;

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
}
