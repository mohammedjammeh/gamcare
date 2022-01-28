package com.projects.gamcare.controllers.doctor;

import com.projects.gamcare.controllers.user.FieldsParent;
import com.projects.gamcare.core.SceneTool;
import com.projects.gamcare.enums.DoctorLevel;
import com.projects.gamcare.enums.UserType;
import com.projects.gamcare.models.*;
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

public class Create extends FieldsParent {
    private Hospital hospital;

    private List<Model> studentDoctors, juniorDoctors, seniorDoctors;

    private List<Model> hospitals, specialities;

    @FXML
    protected ListView studentDoctorsListView, juniorDoctorsListView, seniorDoctorsListView;

    @FXML
    protected TextField universityTextField, fieldOfStudyTextField;

    @FXML
    protected ChoiceBox careerLevelChoiceBox, specialityChoiceBox;

    @FXML
    protected ListView hospitalsListView;

    public void setUpAddForm() {
        studentDoctors = (new Doctor()).getAvailableStudents(hospital);
        juniorDoctors = (new Doctor()).getAvailableJuniors(hospital);
        seniorDoctors = (new Doctor()).getAvailableSeniors(hospital);

        studentDoctorsListView.setItems(getFullNames(studentDoctors));
        juniorDoctorsListView.setItems(getFullNames(juniorDoctors));
        seniorDoctorsListView.setItems(getFullNames(seniorDoctors));
    }

    public void setUpCreateForm() {
        hospitals = (new Hospital()).getAll();
        specialities = (new Speciality()).getAll();

        hospitalsListView.setItems(getNames(hospitals));
        specialityChoiceBox.setItems(getNames(specialities));
        careerLevelChoiceBox.setItems(getEnumItems(DoctorLevel.class));

        hospitalsListView.setOrientation(Orientation.HORIZONTAL);
        hospitalsListView.getSelectionModel().select(hospitals.indexOf(hospital));
    }

    @FXML
    protected void onAddDoctorsButtonClick() {
        List<Integer> studentDoctorsIndices = getStudentDoctorsIndices();
        List<Integer> juniorDoctorsIndices = getJuniorDoctorsIndices();
        List<Integer> seniorDoctorsIndices = getSeniorDoctorsIndices();
    }

    private List<Integer> getStudentDoctorsIndices() {
        return studentDoctorsListView.getSelectionModel().getSelectedIndices();
    }

    private List<Integer> getJuniorDoctorsIndices() {
        return juniorDoctorsListView.getSelectionModel().getSelectedIndices();
    }

    private List<Integer> getSeniorDoctorsIndices() {
        return seniorDoctorsListView.getSelectionModel().getSelectedIndices();
    }

    @FXML
    protected void onCreateDoctorButtonClick() {
        (new Doctor()).insert(newDoctorData());

        Doctor newDoctor = getNewDoctor();

        (new User()).insert(
            newUserData(newDoctorUserData(newDoctor))
        );

        for (Integer hospitalInputIndex : hospitalsIndicesInput()) {
            Hospital hospital = (Hospital) hospitals.get(hospitalInputIndex);
            (new Model())
                .setTableName("hospitals_doctors")
                .insert(newHospitalDoctorData(hospital, newDoctor, 0));
        }

        SceneTool.switchToProfile("doctor/show", getAuthUser(), getNewDoctorWithUser());
        SceneTool.closeWindow(hospitalsListView);
    }

    private Map<String, Object> newDoctorData() {
        TreeMap<String, Object> data = new TreeMap<>();

        data.put("school", universityInput());
        data.put("field_of_study", fieldOfStudyInput());
        data.put("career_level", careerLevelInput());
        data.put("specialities_id", getInputId(specialityChoiceBox, specialities));

        return data;
    }

    private Map<String, Object> newDoctorUserData(Doctor doctor) {
        TreeMap<String, Object> data = new TreeMap<>();

        data.put("type", UserType.DOCTOR.name());
        data.put("doctors_id", doctor.idAttribute());

        return data;
    }

    private Doctor getNewDoctor() {
        return (Doctor) (new Doctor())
            .where("school", universityInput())
            .where("field_of_study", fieldOfStudyInput())
            .where("career_level", careerLevelInput())
            .where("specialities_id", getInputId(specialityChoiceBox, specialities))
            .first();
    }

    private Doctor getNewDoctorWithUser() {
        return (Doctor) (new Doctor())
            .with("users")
            .where("school", universityInput())
            .where("field_of_study", fieldOfStudyInput())
            .where("career_level", careerLevelInput())
            .where("specialities_id", getInputId(specialityChoiceBox, specialities))
            .first();
    }

    private String universityInput() {
        return universityTextField.getText();
    }

    private String fieldOfStudyInput() {
        return fieldOfStudyTextField.getText();
    }

    private String careerLevelInput() {
        return careerLevelChoiceBox.getValue();
    }

    private ObservableList<Integer> hospitalsIndicesInput() {
        return hospitalsListView.getSelectionModel().getSelectedIndices();
    }


    /**
     * Getters & Setters
     */
    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
}
