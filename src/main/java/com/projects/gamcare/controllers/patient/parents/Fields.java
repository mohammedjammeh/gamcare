package com.projects.gamcare.controllers.patient.parents;

import com.projects.gamcare.controllers.user.FieldsParent;
import com.projects.gamcare.enums.UserType;
import com.projects.gamcare.models.Hospital;
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
    protected List<Model> hospitals, bloodTypes;

    @FXML
    protected TextField weightTextField, heightTextField;

    @FXML
    protected ChoiceBox hospitalChoiceBox, bloodTypeChoiceBox;


    /**
     * Getters & Setters
     */
    protected Map<String, Object> getInputData() {
        TreeMap<String, Object> data = new TreeMap<>();

        data.put("weight", weightTextField.getText());
        data.put("height", heightTextField.getText());
        data.put("blood_types_id", getInputId(bloodTypeChoiceBox, bloodTypes));
        data.put("hospitals_id", getInputId(hospitalChoiceBox, hospitals));

        return data;
    }

    public void setInputData(ProfileUser profileUser) {
        Patient patient = profileUser.getPatient();
        Date dateOfBirth = (Date) profileUser.getAttribute("date_of_birth");

        firstNameTextField.setText(profileUser.getAttribute("first_name").toString());
        middleNameTextField.setText(profileUser.getAttribute("middle_name").toString());
        lastNameTextField.setText(profileUser.getAttribute("last_name").toString());

        titleChoiceBox.getSelectionModel().select(titles.indexOf(profileUser.getTitle()));
        genderChoiceBox.getSelectionModel().select(genders.indexOf(profileUser.getGender()));
        tribeChoiceBox.getSelectionModel().select(tribes.indexOf(profileUser.getTribe()));

        hospitalChoiceBox.getSelectionModel().select(hospitals.indexOf(patient.getHospital()));
        placeOfBirthTextField.setText(profileUser.getAttribute("place_of_birth").toString());
        dateOfBirthPicker.setValue(dateOfBirth.toLocalDate());

        weightTextField.setText(patient.getAttribute("weight").toString());
        heightTextField.setText(patient.getAttribute("height").toString());
        bloodTypeChoiceBox.getSelectionModel().select(bloodTypes.indexOf(patient.getBloodType()));

        emailAddressTextField.setText(profileUser.getAttribute("email_address").toString());
        phoneNumberTextField.setText(profileUser.getAttribute("phone_number").toString());
        relevantLinkTextField.setText(profileUser.getAttribute("relevant_link").toString());

        compoundNameTextField.setText(profileUser.getAttribute("compound").toString());
        townTextField.setText(profileUser.getAttribute("town").toString());
        regionChoiceBox.getSelectionModel().select(regions.indexOf(profileUser.getRegion()));

        otherDetailsTextArea.setText(profileUser.getAttribute("other_details").toString());
    }

    protected Map<String, Object> newPatientUserData(Patient patient) {
        TreeMap<String, Object> data = new TreeMap<>();

        data.put("type", UserType.PATIENT.name());
        data.put("patients_id", patient.idAttribute());

        return data;
    }

    protected Patient getPatientFromDB() {
        return (Patient) (new Patient())
            .where("weight", weightTextField.getText())
            .where("height", heightTextField.getText())
            .where("blood_types_id", getInputId(bloodTypeChoiceBox, bloodTypes))
            .where("hospitals_id", getInputId(hospitalChoiceBox, hospitals))
            .first();
    }

    protected Patient getPatientWithUserFromDB() {
        return (Patient) (new Patient())
            .with("users")
            .where("weight", weightTextField.getText())
            .where("height", heightTextField.getText())
            .where("blood_types_id", getInputId(bloodTypeChoiceBox, bloodTypes))
            .where("hospitals_id", getInputId(hospitalChoiceBox, hospitals))
            .first();
    }
}
