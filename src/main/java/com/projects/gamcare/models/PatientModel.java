package com.projects.gamcare.models;

import com.projects.gamcare.models.main.UserModel;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class PatientModel extends UserModel {
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
