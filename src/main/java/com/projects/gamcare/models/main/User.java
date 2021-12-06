package com.projects.gamcare.models.main;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class User extends Base {
    @FXML
    protected TextField firstNameTextField;

    @FXML
    protected TextField middleNameTextField;

    @FXML
    protected TextField lastNameTextField;

    @FXML
    protected ChoiceBox<String> titleChoiceBox;

    @FXML
    protected ChoiceBox<String> genderChoiceBox;

    @FXML
    protected ChoiceBox<String> tribeChoiceBox;

    @FXML
    protected TextField emailAddressTextField;

    @FXML
    protected TextField phoneNumberTextField;

    @FXML
    protected TextField relevantLinkTextField;

    @FXML
    protected TextField placeOfBirthTextField;

    @FXML
    protected DatePicker dateOfBirthPicker;

    @FXML
    protected TextField compoundNameTextField;

    @FXML
    protected TextField townTextField;

    @FXML
    protected ChoiceBox<String> regionChoiceBox;

    @FXML
    protected TextArea otherDetailsTextArea;

    public String getFirstName() {
        return firstNameTextField.getText();
    }

    public String getMiddleName() {
        return middleNameTextField.getText();
    }

    public String getLastName() {
        return lastNameTextField.getText();
    }

    public Integer getTitleIndex() {
        return titleChoiceBox.getSelectionModel().getSelectedIndex();
    }

    public Integer getGenderIndex() {
        return genderChoiceBox.getSelectionModel().getSelectedIndex();
    }

    public Integer getTribeIndex() {
        return tribeChoiceBox.getSelectionModel().getSelectedIndex();
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

    public String getPlaceOfBirth() {
        return placeOfBirthTextField.getText();
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirthPicker.getValue();
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
