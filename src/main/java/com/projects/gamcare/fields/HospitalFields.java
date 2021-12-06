package com.projects.gamcare.fields;

import com.projects.gamcare.fields.main.BaseFields;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class HospitalFields extends BaseFields {
    @FXML
    protected TextField nameTextField;

    @FXML
    protected ChoiceBox<String> sizeChoiceBox;

    @FXML
    protected ChoiceBox<String> leadDoctorChoiceBox;

    @FXML
    protected TextField emailAddressTextField;

    @FXML
    protected TextField phoneNumberTextField;

    @FXML
    protected TextField relevantLinkTextField;

    @FXML
    protected TextField compoundNameTextField;

    @FXML
    protected TextField townTextField;

    @FXML
    protected ChoiceBox<String> regionChoiceBox;

    @FXML
    protected TextArea otherDetailsTextArea;

    public String getName() {
        return nameTextField.getText();
    }

    public String getSize() {
        return sizeChoiceBox.getValue();
    }

    public Integer getLeadDoctorIndex() {
        return leadDoctorChoiceBox.getSelectionModel().getSelectedIndex();
    }

    public String getEmailAddress() {
        return emailAddressTextField.getText();
    }

    public String getPhoneNumber() {
        return phoneNumberTextField.getText();
    }

    public String getRelevantLink() {
        return relevantLinkTextField.getText();
    }

    public String getCompoundName() {
        return compoundNameTextField.getText();
    }

    public String getTown() {
        return townTextField.getText();
    }

    public Integer getRegionIndex() {
        return regionChoiceBox.getSelectionModel().getSelectedIndex();
    }

    public String getOtherDetails() {
        return otherDetailsTextArea.getText();
    }
}
