package com.projects.gamcare.controllers.user;

import com.projects.gamcare.core.Controller;
import com.projects.gamcare.core.Hash;
import com.projects.gamcare.core.TimeTool;
import com.projects.gamcare.enums.UserType;
import com.projects.gamcare.models.*;
import com.projects.gamcare.models.main.Model;
import com.projects.gamcare.wrappers.ChoiceBox;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CreateFieldsParent extends Controller {
    protected List<Model> titles, genders, tribes, regions;

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

    public Map<String, Object> newUserData() {
        TreeMap<String, Object> data = new TreeMap<>();
        byte[] salt = Hash.createSalt();

        data.put("first_name", firstNameInput());
        data.put("middle_name", middleNameInput());
        data.put("last_name", lastNameInput());

        data.put("titles_id", titles.get(titleIndexInput()).idAttribute());
        data.put("genders_id", genders.get(genderIndexInput()).idAttribute());
        data.put("tribes_id", tribes.get(tribeIndexInput()).idAttribute());

        data.put("email_address", emailAddressInput());
        data.put("phone_number", phoneNumberInput());
        data.put("relevant_link", relevantLinkInput());

        data.put("place_of_birth", placeOfBirthInput());
        data.put("date_of_birth", dateOfBirthInput().toString());

        data.put("compound", compoundNameInput());
        data.put("town", townInput());
        data.put("regions_id", regions.get(regionIndexInput()).idAttribute());

        data.put("other_details", otherDetailsInput());
        data.put("salt", salt);
        data.put("hash", Hash.generate("password123", salt));

        data.put("created_at", TimeTool.newDate());
        data.put("updated_at", TimeTool.newDate());

        return data;
    }

    protected String firstNameInput() {
        return firstNameTextField.getText();
    }

    protected String middleNameInput() {
        return middleNameTextField.getText();
    }

    protected String lastNameInput() {
        return lastNameTextField.getText();
    }

    protected Integer titleIndexInput() {
        return titleChoiceBox.getSelectionModel().getSelectedIndex();
    }

    protected Integer genderIndexInput() {
        return genderChoiceBox.getSelectionModel().getSelectedIndex();
    }

    protected Integer tribeIndexInput() {
        return tribeChoiceBox.getSelectionModel().getSelectedIndex();
    }

    protected String emailAddressInput() {
        return emailAddressTextField.getText();
    }

    protected String phoneNumberInput() {
        return phoneNumberTextField.getText();
    }

    protected String relevantLinkInput() {
        return relevantLinkTextField.getText();
    }

    protected String placeOfBirthInput() {
        return placeOfBirthTextField.getText();
    }

    protected LocalDate dateOfBirthInput() {
        return dateOfBirthPicker.getValue();
    }

    protected String compoundNameInput() {
        return compoundNameTextField.getText();
    }

    protected String townInput() {
        return townTextField.getText();
    }

    protected Integer regionIndexInput() {
        return regionChoiceBox.getSelectionModel().getSelectedIndex();
    }

    protected String otherDetailsInput() {
        return otherDetailsTextArea.getText();
    }
}
