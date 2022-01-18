package com.projects.gamcare.controllers.hospital;

import com.projects.gamcare.enums.HospitalSize;
import com.projects.gamcare.core.Controller;
import com.projects.gamcare.models.Doctor;
import com.projects.gamcare.models.Region;
import com.projects.gamcare.models.main.Model;
import com.projects.gamcare.wrappers.ChoiceBox;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Optional;

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

    protected List<Model> leadDoctors;

    public void initialize() {
        hideErrorBox();

        leadDoctors = (new Doctor()).getAvailableSeniors();

        regionChoiceBox.setItems((new Region()).getAllNames());
        sizeChoiceBox.setItems(getEnumItems(HospitalSize.class));
        leadDoctorChoiceBox.setItems(leadDoctors
            .stream()
            .map(Model::fullNameAttribute)
            .toList()
        );
    }

    public String nameInput() {
        return nameTextField.getText();
    }

    public String sizeInput() {
        return sizeChoiceBox.getValue();
    }

    public Integer leadDoctorIndexInput() {
        return leadDoctorChoiceBox.getSelectionModel().getSelectedIndex();
    }

    public String emailAddressInput() {
        return emailAddressTextField.getText();
    }

    public String phoneNumberInput() {
        return phoneNumberTextField.getText();
    }

    public String relevantLinkInput() {
        return relevantLinkTextField.getText();
    }

    public String compoundNameInput() {
        return compoundNameTextField.getText();
    }

    public String townInput() {
        return townTextField.getText();
    }

    public Integer regionIndexInput() {
        return regionChoiceBox.getSelectionModel().getSelectedIndex();
    }

    public String otherDetailsInput() {
        return otherDetailsTextArea.getText();
    }
}
