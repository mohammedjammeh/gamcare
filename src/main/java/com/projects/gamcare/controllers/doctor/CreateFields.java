package com.projects.gamcare.controllers.doctor;

import com.projects.gamcare.enums.DoctorLevel;
import com.projects.gamcare.controllers.user.CreateFieldsParent;
import com.projects.gamcare.models.Doctor;
import com.projects.gamcare.models.Hospital;
import com.projects.gamcare.models.Speciality;
import com.projects.gamcare.models.main.Model;
import com.projects.gamcare.wrappers.ChoiceBox;
import com.projects.gamcare.wrappers.ListView;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.TextField;

import java.util.List;

public class CreateFields extends CreateFieldsParent {
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

    protected List<Model> studentDoctors, juniorDoctors, seniorDoctors;

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

        studentDoctors = (new Doctor()).getAvailableStudents(1);
        juniorDoctors = (new Doctor()).getAvailableJuniors(1);
        seniorDoctors = (new Doctor()).getAvailableSeniors(1);

        studentDoctorsListView.setItems(getFullNames(studentDoctors));
        juniorDoctorsListView.setItems(getFullNames(juniorDoctors));
        seniorDoctorsListView.setItems(getFullNames(seniorDoctors));

//        hospitalsListView.setItems((new Hospital()).getAllNames());
//        specialityChoiceBox.setItems((new Speciality()).getAllNames());
        careerLevelChoiceBox.setItems(getEnumItems(DoctorLevel.class));

        hospitalsListView.setOrientation(Orientation.HORIZONTAL);
    }
}
