package com.projects.gamcare.controllers.patient;

import com.projects.gamcare.controllers.user.CreateFieldsParent;
import com.projects.gamcare.models.BloodType;
import com.projects.gamcare.models.Hospital;
import com.projects.gamcare.wrappers.ChoiceBox;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreateFields extends CreateFieldsParent {
    @FXML
    protected ChoiceBox hospitalChoiceBox;

    @FXML
    protected TextField weightTextField;

    @FXML
    protected TextField heightTextField;

    @FXML
    protected ChoiceBox bloodTypeChoiceBox;

    public Integer hospitalIndexInput() {
        return hospitalChoiceBox.getSelectionModel().getSelectedIndex();
    }

    public String weightInput() {
        return weightTextField.getText();
    }

    public String heightInput() {
        return heightTextField.getText();
    }

    public Integer bloodTypeInputId() {
        return selectedInputId(bloodTypeChoiceBox);
    }

    public void initialize() {
        super.initialize();

//        hospitalChoiceBox.setItems((new Hospital()).getAllNames());
//        bloodTypeChoiceBox.setItems((new BloodType()).getAllNames());
    }
}
