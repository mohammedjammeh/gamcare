package com.projects.gamcare.controllers.doctor;

import com.projects.gamcare.controllers.doctor.parents.Fields;
import com.projects.gamcare.core.SceneTool;
import com.projects.gamcare.models.*;
import com.projects.gamcare.models.main.Model;
import com.projects.gamcare.wrappers.ListView;
import javafx.fxml.FXML;

import java.util.List;

public class Create extends Fields {
    private List<Model> studentDoctors, juniorDoctors, seniorDoctors;

    @FXML
    protected ListView studentDoctorsListView, juniorDoctorsListView, seniorDoctorsListView;

    public void setUpAddForm() {
        studentDoctors = (new Doctor()).getAvailableStudents(hospital);
        juniorDoctors = (new Doctor()).getAvailableJuniors(hospital);
        seniorDoctors = (new Doctor()).getAvailableSeniors(hospital);

        studentDoctorsListView.setItems(getFullNames(studentDoctors));
        juniorDoctorsListView.setItems(getFullNames(juniorDoctors));
        seniorDoctorsListView.setItems(getFullNames(seniorDoctors));
    }

    public void setUpCreateForm() {
        setUpForm();
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
        (new Doctor()).insert(getInputData());

        Doctor newDoctor = getDoctorFromDB();

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
}
