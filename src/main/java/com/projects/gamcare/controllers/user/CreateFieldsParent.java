package com.projects.gamcare.controllers.user;

import com.projects.gamcare.core.Controller;
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

public class CreateFieldsParent extends Controller {
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

    public void initialize() {
        hideErrorBox();

        titleChoiceBox.setItems((new Title()).getAllNames());
        genderChoiceBox.setItems((new Gender()).getAllNames());
        tribeChoiceBox.setItems((new Tribe()).getAllNames());
        regionChoiceBox.setItems((new Region()).getAllNames());
    }

    public String firstNameInput() {
        return firstNameTextField.getText();
    }

    public String middleNameInput() {
        return middleNameTextField.getText();
    }

    public String lastNameInput() {
        return lastNameTextField.getText();
    }

    public Integer titleInputId() {
        return inputId(titleChoiceBox);
    }

    public Integer genderInputId() {
        return inputId(genderChoiceBox);
    }

    public Integer tribeInputId() {
        return inputId(tribeChoiceBox);
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

    public String placeOfBirthInput() {
        return placeOfBirthTextField.getText();
    }

    public LocalDate dateOfBirthIput() {
        return dateOfBirthPicker.getValue();
    }

    public String compoundNameInput() {
        return compoundNameTextField.getText();
    }

    public String townInput() {
        return townTextField.getText();
    }

    public Integer regionInputId() {
        return inputId(regionChoiceBox);
    }

    public String otherDetailsInput() {
        return otherDetailsTextArea.getText();
    }

    public Integer inputId(ChoiceBox choiceBox)
    {
        int choiceBoxIndex = choiceBox.getSelectionModel().getSelectedIndex();
        choiceBoxIndex++;

        return choiceBoxIndex;
    }
}
