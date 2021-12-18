package com.projects.gamcare.fields.main;

import com.projects.gamcare.models.Gender;
import com.projects.gamcare.models.Region;
import com.projects.gamcare.models.Title;
import com.projects.gamcare.models.Tribe;
import com.projects.gamcare.wrappers.ChoiceBox;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class UserFields extends BaseFields {
    @FXML
    protected TextField firstNameTextField;

    @FXML
    protected TextField middleNameTextField;

    @FXML
    protected TextField lastNameTextField;

    @FXML
    protected ChoiceBox titleChoiceBox;

    @FXML
    protected ChoiceBox genderChoiceBox;

    @FXML
    protected ChoiceBox tribeChoiceBox;

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
    protected ChoiceBox regionChoiceBox;

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

    public void initialize() {
        super.initialize();

        titleChoiceBox.setItems((new Title()).getNames());
//        genderChoiceBox.setItems(Gender.getItems());
//        tribeChoiceBox.setItems(Tribe.getItems());
//        regionChoiceBox.setItems(Region.getItems());
    }
}
