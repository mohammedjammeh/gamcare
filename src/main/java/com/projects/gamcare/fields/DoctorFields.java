package com.projects.gamcare.fields;

import com.projects.gamcare.core.DB;
import com.projects.gamcare.enums.DoctorLevel;
import com.projects.gamcare.fields.main.UserFields;
import com.projects.gamcare.wrappers.ChoiceBox;
import com.projects.gamcare.wrappers.ListView;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class DoctorFields extends UserFields {
    @FXML
    protected ListView studentDoctorsListView;

    @FXML
    protected ListView juniorDoctorsListView;

    @FXML
    protected ListView seniorDoctorsListView;

    @FXML
    protected TextField universityTextField;

    @FXML
    protected TextField fieldOfStudyTextField;

    @FXML
    protected ChoiceBox careerLevelChoiceBox;

    @FXML
    protected ChoiceBox specialityChoiceBox;

    @FXML
    protected ListView hospitalsListView;

    public String getUniversity() {
        return universityTextField.getText();
    }

    public String getFieldOfStudy() {
        return fieldOfStudyTextField.getText();
    }

    public String getCareerLevel() {
        return careerLevelChoiceBox.getValue();
    }

    public Integer getSpecialityIndex() {
        return specialityChoiceBox.getSelectionModel().getSelectedIndex();
    }

    public List<Integer> getStudentDoctorsIndices() {
        return studentDoctorsListView.getSelectionModel().getSelectedIndices();
    }

    public List<Integer> getJuniorDoctorsIndices() {
        return juniorDoctorsListView.getSelectionModel().getSelectedIndices();
    }

    public List<Integer> getSeniorDoctorsIndices() {
        return seniorDoctorsListView.getSelectionModel().getSelectedIndices();
    }

    public void initialize() {
        super.initialize();

        specialityChoiceBox.setItems(getDatabaseItems("specialities"));
        careerLevelChoiceBox.setItems(getEnumItems(DoctorLevel.class));

        hospitalsListView.setItems(getDatabaseItems("hospitals"));
        hospitalsListView.setOrientation(Orientation.HORIZONTAL);

//        only doctors who are not assigned to other 3 or more hospitals
//        only doctors are not already assigned to the current hospital

//        studentDoctorsListView
//        juniorDoctorsListView
//        seniorDoctorsListView



        List<Map<String, String>> doctors = getOtherHospitalDoctors(DoctorLevel.JUNIOR.toString());

        List<String> availableDoctors = doctors.stream()
            .filter(doctor -> isAvailable(doctor, doctors))
            .map(this::getFullName)
            .toList();

        System.out.println(availableDoctors);
    }

    public List<Map<String, String>> getOtherHospitalDoctors(String level) {
        List<String> fields = List.of("*");

        return new DB()
            .select(fields)
            .from("doctors")
            .with("users")
            .with("hospitals_doctors")
            .where("hospitals_doctors.hospitals_id", "!=", "1")
            .where("doctors.career_level", "=", level)
            .orderBy("first_name")
            .get();
    }

    public Boolean isAvailable(Map<String, String> doctor, List<Map<String, String>> doctors) {
        return doctors.stream()
            .filter(filteredDoctor -> Objects.equals(filteredDoctor.get("id"), doctor.get("id")))
            .count() < 3;
    }

    public String getFullName(Map<String, String> doctor) {
        return doctor.get("first_name") + " " + doctor.get("last_name");
    }
}
