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

//        SELECT * FROM doctors INNER JOIN users ON doctors.id = users.doctors_id INNER JOIN hospitals_doctors ON doctors.id = hospitals_doctors.doctors_id WHERE hospitals_doctors.hospitals_id != 1;
//        List<String> doctors = new DB()
//                .select(List.of("*"))
//                .from(mainTable)
//                .with(withTable)
//                .where(whereColumn, "=", whereValue)
//                .orderBy("first_name")
//                .get()
//                .stream()
//                .map(item -> item.get("first_name") + " " + item.get("last_name"))
//                .toList();
//
//        System.out.println(doctors);
    }
}
