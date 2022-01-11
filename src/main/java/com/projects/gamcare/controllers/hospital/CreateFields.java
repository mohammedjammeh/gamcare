package com.projects.gamcare.controllers.hospital;

import com.projects.gamcare.enums.HospitalSize;
import com.projects.gamcare.core.Controller;
import com.projects.gamcare.models.Doctor;
import com.projects.gamcare.models.Region;
import com.projects.gamcare.wrappers.ChoiceBox;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateFields extends Controller {
    @FXML
    protected TextField nameTextField;

    @FXML
    protected ChoiceBox sizeChoiceBox;

    @FXML
    protected ChoiceBox leadDoctorChoiceBox;

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
    protected ChoiceBox regionChoiceBox;

    @FXML
    protected TextArea otherDetailsTextArea;

    public void initialize() {
        hideErrorBox();

        regionChoiceBox.setItems((new Region()).getAllNames());
        sizeChoiceBox.setItems(getEnumItems(HospitalSize.class));
        leadDoctorChoiceBox.setItems((new Doctor()).getAvailableSeniors());
    }

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
