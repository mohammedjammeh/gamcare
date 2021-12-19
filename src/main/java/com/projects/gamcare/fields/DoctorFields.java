package com.projects.gamcare.fields;

import com.projects.gamcare.enums.DoctorLevel;
import com.projects.gamcare.fields.main.UserFields;
import com.projects.gamcare.models.Doctor;
import com.projects.gamcare.models.Hospital;
import com.projects.gamcare.models.Speciality;
import com.projects.gamcare.models.Title;
import com.projects.gamcare.wrappers.ChoiceBox;
import com.projects.gamcare.wrappers.ListView;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.TextField;

import java.util.List;

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

        specialityChoiceBox.setItems((new Speciality()).getNames());
        careerLevelChoiceBox.setItems(getEnumItems(DoctorLevel.class));

        hospitalsListView.setItems((new Hospital()).getNames());
        hospitalsListView.setOrientation(Orientation.HORIZONTAL);

        studentDoctorsListView.setItems((new Doctor()).getAvailableStudents(1));
        juniorDoctorsListView.setItems((new Doctor()).getAvailableJuniors(1));
        seniorDoctorsListView.setItems((new Doctor()).getAvailableSeniors(1));
    }
}
