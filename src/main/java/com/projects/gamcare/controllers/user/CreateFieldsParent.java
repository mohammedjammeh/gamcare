package com.projects.gamcare.controllers.user;

import com.projects.gamcare.core.Controller;
import com.projects.gamcare.models.*;
import com.projects.gamcare.models.main.Model;
import com.projects.gamcare.wrappers.ChoiceBox;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.List;

public class CreateFieldsParent extends Controller {
    @FXML
    protected TextField firstNameTextField, middleNameTextField, lastNameTextField;

    @FXML
    protected ChoiceBox titleChoiceBox, genderChoiceBox, tribeChoiceBox, regionChoiceBox;

    @FXML
    protected TextField emailAddressTextField, phoneNumberTextField, relevantLinkTextField;

    @FXML
    protected TextField placeOfBirthTextField, compoundNameTextField, townTextField;

    @FXML
    protected DatePicker dateOfBirthPicker;

    @FXML
    protected TextArea otherDetailsTextArea;

    protected List<Model> titles, genders, tribes, regions;

    public void initialize() {
        hideErrorBox();

        titles = (new Title()).getAll();
        genders = (new Gender()).getAll();
        tribes = (new Tribe()).getAll();
        regions = (new Region()).getAll();

        titleChoiceBox.setItems(getNames(titles));
        genderChoiceBox.setItems(getNames(genders));
        tribeChoiceBox.setItems(getNames(tribes));
        regionChoiceBox.setItems(getNames(regions));
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

    public Integer titleIndexInput() {
        return titleChoiceBox.getSelectionModel().getSelectedIndex();
    }

    public Integer genderIndexInput() {
        return genderChoiceBox.getSelectionModel().getSelectedIndex();
    }

    public Integer tribeIndexInput() {
        return tribeChoiceBox.getSelectionModel().getSelectedIndex();
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

    public Integer regionIndexInput() {
        return regionChoiceBox.getSelectionModel().getSelectedIndex();
    }

    public String otherDetailsInput() {
        return otherDetailsTextArea.getText();
    }

}
