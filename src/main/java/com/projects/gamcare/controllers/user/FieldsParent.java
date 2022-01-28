package com.projects.gamcare.controllers.user;

import com.projects.gamcare.core.Controller;
import com.projects.gamcare.core.Hash;
import com.projects.gamcare.core.TimeTool;
import com.projects.gamcare.models.*;
import com.projects.gamcare.models.main.Model;
import com.projects.gamcare.wrappers.ChoiceBox;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FieldsParent extends Controller {
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

    protected Map<String, Object> newUserData(Map<String, Object> extraUserData) {
        TreeMap<String, Object> data = new TreeMap<>();
        byte[] salt = Hash.createSalt();

        data.put("first_name", firstNameTextField.getText());
        data.put("middle_name", middleNameTextField.getText());
        data.put("last_name", lastNameTextField.getText());

        data.put("titles_id", titles.get(titleIndexInput()).idAttribute());
        data.put("genders_id", genders.get(genderIndexInput()).idAttribute());
        data.put("tribes_id", tribes.get(tribeIndexInput()).idAttribute());

        data.put("email_address", emailAddressTextField.getText());
        data.put("phone_number", phoneNumberTextField.getText());
        data.put("relevant_link", relevantLinkTextField.getText());

        data.put("place_of_birth", placeOfBirthTextField.getText());
        data.put("date_of_birth", Date.valueOf(dateOfBirthPicker.getValue()));

        data.put("compound", compoundNameTextField.getText());
        data.put("town", townTextField.getText());
        data.put("regions_id", regions.get(regionIndexInput()).idAttribute());

        data.put("other_details", otherDetailsTextArea.getText());
        data.put("salt", salt);
        data.put("hash", Hash.generate("password123", salt));

        data.put("created_at", TimeTool.newDate());
        data.put("updated_at", TimeTool.newDate());

        data.putAll(extraUserData);

        return data;
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

    protected Integer regionIndexInput() {
        return regionChoiceBox.getSelectionModel().getSelectedIndex();
    }


    /**
     * Getters & Setters
     */
    public void setUpAddForm() {}

    public void setUpCreateForm() {}

    public void setUpEditForm() {}
}
