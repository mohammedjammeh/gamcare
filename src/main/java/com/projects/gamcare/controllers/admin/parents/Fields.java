package com.projects.gamcare.controllers.admin.parents;

import com.projects.gamcare.controllers.user.FieldsParent;
import com.projects.gamcare.enums.UserType;
import com.projects.gamcare.models.Patient;
import com.projects.gamcare.models.main.Model;
import com.projects.gamcare.models.main.ProfileUser;
import com.projects.gamcare.wrappers.ChoiceBox;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Fields extends FieldsParent {

    /**
     * Getters & Setters
     */
    public void setInputData(ProfileUser profileUser) {
        Date dateOfBirth = (Date) profileUser.getAttribute("date_of_birth");

        firstNameTextField.setText(profileUser.getAttribute("first_name").toString());
        middleNameTextField.setText(profileUser.getAttribute("middle_name").toString());
        lastNameTextField.setText(profileUser.getAttribute("last_name").toString());

        titleChoiceBox.getSelectionModel().select(titles.indexOf(profileUser.getTitle()));
        genderChoiceBox.getSelectionModel().select(genders.indexOf(profileUser.getGender()));
        tribeChoiceBox.getSelectionModel().select(tribes.indexOf(profileUser.getTribe()));

        emailAddressTextField.setText(profileUser.getAttribute("email_address").toString());
        phoneNumberTextField.setText(profileUser.getAttribute("phone_number").toString());
        relevantLinkTextField.setText(profileUser.getAttribute("relevant_link").toString());

        compoundNameTextField.setText(profileUser.getAttribute("compound").toString());
        townTextField.setText(profileUser.getAttribute("town").toString());
        regionChoiceBox.getSelectionModel().select(regions.indexOf(profileUser.getRegion()));

        placeOfBirthTextField.setText(profileUser.getAttribute("place_of_birth").toString());
        dateOfBirthPicker.setValue(dateOfBirth.toLocalDate());

        otherDetailsTextArea.setText(profileUser.getAttribute("other_details").toString());
    }
}
