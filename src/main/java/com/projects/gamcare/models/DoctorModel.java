package com.projects.gamcare.models;

import com.projects.gamcare.models.main.UserModel;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class DoctorModel extends UserModel {
    @FXML
    protected ListView<String> juniorDoctorsListView;

    @FXML
    protected ListView<String> studentDoctorsListView;

    @FXML
    protected ListView<String> seniorDoctorsListView;

    @FXML
    protected TextField universityTextField;

    @FXML
    protected TextField fieldOfStudyTextField;

    @FXML
    protected ChoiceBox<String> careerLevelChoiceBox;

    @FXML
    protected ChoiceBox<String> specialityChoiceBox;

    @FXML
    protected ListView<String> hospitalsListView;

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
}
