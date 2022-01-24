package com.projects.gamcare.controllers.doctor;

import com.projects.gamcare.enums.DoctorLevel;
import com.projects.gamcare.controllers.user.CreateFieldsParent;
import com.projects.gamcare.enums.UserType;
import com.projects.gamcare.models.Doctor;
import com.projects.gamcare.models.Hospital;
import com.projects.gamcare.models.Speciality;
import com.projects.gamcare.models.main.Model;
import com.projects.gamcare.wrappers.ChoiceBox;
import com.projects.gamcare.wrappers.ListView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CreateFields extends CreateFieldsParent {
    protected List<Model> studentDoctors, juniorDoctors, seniorDoctors;

    protected List<Model> hospitals, specialities;

    @FXML
    protected ListView studentDoctorsListView, juniorDoctorsListView, seniorDoctorsListView;

    @FXML
    protected TextField universityTextField, fieldOfStudyTextField;

    @FXML
    protected ChoiceBox careerLevelChoiceBox, specialityChoiceBox;

    @FXML
    protected ListView hospitalsListView;

    public void initialize() {
        super.initialize();

//        studentDoctors = (new Doctor()).getAvailableStudents(1);
//        juniorDoctors = (new Doctor()).getAvailableJuniors(1);
//        seniorDoctors = (new Doctor()).getAvailableSeniors(1);
        hospitals = (new Hospital()).getAll();
        specialities = (new Speciality()).getAll();


//        studentDoctorsListView.setItems(getFullNames(studentDoctors));
//        juniorDoctorsListView.setItems(getFullNames(juniorDoctors));
//        seniorDoctorsListView.setItems(getFullNames(seniorDoctors));
        hospitalsListView.setItems(getNames(hospitals));
        specialityChoiceBox.setItems(getNames(specialities));
        careerLevelChoiceBox.setItems(getEnumItems(DoctorLevel.class));

        hospitalsListView.setOrientation(Orientation.HORIZONTAL);
    }

    protected Map<String, Object> newDoctorData() {
        TreeMap<String, Object> data = new TreeMap<>();

        data.put("school", universityInput());
        data.put("field_of_study", fieldOfStudyInput());
        data.put("career_level", careerLevelInput());
        data.put("specialities_id", specialities.get(specialityIndexInput()).idAttribute());

        return data;
    }

    protected Map<String, Object> newUserData(Doctor newDoctor) {
        Map<String, Object> data = super.newUserData();

        data.put("type", UserType.DOCTOR.name());
        data.put("doctors_id", newDoctor.idAttribute());

        return data;
    }

    protected List<Integer> getStudentDoctorsIndices() {
        return studentDoctorsListView.getSelectionModel().getSelectedIndices();
    }

    protected List<Integer> getJuniorDoctorsIndices() {
        return juniorDoctorsListView.getSelectionModel().getSelectedIndices();
    }

    protected List<Integer> getSeniorDoctorsIndices() {
        return seniorDoctorsListView.getSelectionModel().getSelectedIndices();
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

    protected Integer specialityIndexInput() {
        return specialityChoiceBox.getSelectionModel().getSelectedIndex();
    }

    protected ObservableList<Integer> hospitalsIndicesInput() {
        return hospitalsListView.getSelectionModel().getSelectedIndices();
    }
}
