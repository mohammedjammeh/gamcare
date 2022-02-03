package com.projects.gamcare.controllers.doctor.parents;

import com.projects.gamcare.controllers.user.FieldsParent;
import com.projects.gamcare.core.StringTool;
import com.projects.gamcare.enums.DoctorLevel;
import com.projects.gamcare.enums.UserType;
import com.projects.gamcare.models.Doctor;
import com.projects.gamcare.models.Hospital;
import com.projects.gamcare.models.Speciality;
import com.projects.gamcare.models.main.Model;
import com.projects.gamcare.models.main.ProfileUser;
import com.projects.gamcare.wrappers.ChoiceBox;
import com.projects.gamcare.wrappers.ListView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class Fields extends FieldsParent {
    protected Hospital hospital;

    protected List<Model> hospitals, specialities;

    @FXML
    protected TextField universityTextField, fieldOfStudyTextField;

    @FXML
    protected ChoiceBox careerLevelChoiceBox, specialityChoiceBox;

    @FXML
    protected ListView hospitalsListView;

    public void setUpForm() {
        hospitals = (new Hospital()).getAll();
        specialities = (new Speciality()).getAll();

        hospitalsListView.setItems(getNames(hospitals));
        specialityChoiceBox.setItems(getNames(specialities));
        careerLevelChoiceBox.setItems(getEnumItems(DoctorLevel.class));

        hospitalsListView.setOrientation(Orientation.HORIZONTAL);
        hospitalsListView.getSelectionModel().select(hospitals.indexOf(hospital));
    }

    /**
     * Getters & Setters
     */
    protected Map<String, Object> getInputData() {
        TreeMap<String, Object> data = new TreeMap<>();

        data.put("school", universityInput());
        data.put("field_of_study", fieldOfStudyInput());
        data.put("career_level", careerLevelInput());
        data.put("specialities_id", getInputId(specialityChoiceBox, specialities));

        return data;
    }

    public void setInputData(ProfileUser profileUser) {
        Doctor doctor = profileUser.getDoctor();
        Date dateOfBirth = (Date) profileUser.getAttribute("date_of_birth");
        String careerLevel = StringTool.capitalise(profileUser.getAttribute("career_level").toString());
        List<Integer> hospitalsIDs = doctor.getHospitals().stream().map(Model::idAttribute).toList();

        firstNameTextField.setText(profileUser.getAttribute("first_name").toString());
        middleNameTextField.setText(profileUser.getAttribute("middle_name").toString());
        lastNameTextField.setText(profileUser.getAttribute("last_name").toString());

        titleChoiceBox.getSelectionModel().select(titles.indexOf(profileUser.getTitle()));
        genderChoiceBox.getSelectionModel().select(genders.indexOf(profileUser.getGender()));
        tribeChoiceBox.getSelectionModel().select(tribes.indexOf(profileUser.getTribe()));

        universityTextField.setText(profileUser.getAttribute("school").toString());
        fieldOfStudyTextField.setText(profileUser.getAttribute("field_of_study").toString());
        careerLevelChoiceBox.setValue(careerLevel);

        specialityChoiceBox.getSelectionModel().select(specialities.indexOf(doctor.getSpeciality()));
        placeOfBirthTextField.setText(profileUser.getAttribute("place_of_birth").toString());
        dateOfBirthPicker.setValue(dateOfBirth.toLocalDate());

        emailAddressTextField.setText(profileUser.getAttribute("email_address").toString());
        phoneNumberTextField.setText(profileUser.getAttribute("phone_number").toString());
        relevantLinkTextField.setText(profileUser.getAttribute("relevant_link").toString());

        compoundNameTextField.setText(profileUser.getAttribute("compound").toString());
        townTextField.setText(profileUser.getAttribute("town").toString());
        regionChoiceBox.getSelectionModel().select(regions.indexOf(profileUser.getRegion()));

        otherDetailsTextArea.setText(profileUser.getAttribute("other_details").toString());

        for (Integer hospitalID : hospitalsIDs) {
            hospitalID--;
            hospitalsListView.getSelectionModel().select(hospitalID);
        }
    }

    protected Map<String, Object> newDoctorUserData(Doctor doctor) {
        TreeMap<String, Object> data = new TreeMap<>();

        data.put("type", UserType.DOCTOR.name());
        data.put("doctors_id", doctor.idAttribute());

        return data;
    }

    protected Doctor getDoctorFromDB() {
        return (Doctor) (new Doctor())
            .where("school", universityInput())
            .where("field_of_study", fieldOfStudyInput())
            .where("career_level", careerLevelInput())
            .where("specialities_id", getInputId(specialityChoiceBox, specialities))
            .first();
    }

    protected Doctor getNewDoctorWithUser() {
        return (Doctor) (new Doctor())
            .with("users")
            .where("school", universityInput())
            .where("field_of_study", fieldOfStudyInput())
            .where("career_level", careerLevelInput())
            .where("specialities_id", getInputId(specialityChoiceBox, specialities))
            .first();
    }

    protected String universityInput() {
        return universityTextField.getText();
    }

    protected String fieldOfStudyInput() {
        return fieldOfStudyTextField.getText();
    }

    protected String careerLevelInput() {
        return careerLevelChoiceBox.getValue();
    }

    protected ObservableList<Integer> hospitalsIndicesInput() {
        return hospitalsListView.getSelectionModel().getSelectedIndices();
    }


    /**
     * Getters & Setters
     */
    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
}
