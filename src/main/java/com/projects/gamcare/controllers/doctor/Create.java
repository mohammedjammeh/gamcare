package com.projects.gamcare.controllers.doctor;

import com.projects.gamcare.controllers.doctor.parents.Fields;
import com.projects.gamcare.core.SceneTool;
import com.projects.gamcare.models.*;
import com.projects.gamcare.models.main.Model;
import com.projects.gamcare.wrappers.ListView;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class Create extends Fields {
    private List<Model> studentDoctors, juniorDoctors, seniorDoctors;

    @FXML
    protected HBox addDoctorsSubHeadingBox;

    @FXML
    protected VBox addDoctorsForm;

    @FXML
    protected ListView studentDoctorsListView, juniorDoctorsListView, seniorDoctorsListView;

    public void initialize() {
        super.initialize();

        hide(addDoctorsSubHeadingBox);
        hide(addDoctorsForm);
    }

    public void setUpAddForm() {
        show(addDoctorsSubHeadingBox);
        show(addDoctorsForm);

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
        addDoctorsHospital(studentDoctorsListView, studentDoctors);
        addDoctorsHospital(juniorDoctorsListView, juniorDoctors);
        addDoctorsHospital(seniorDoctorsListView, seniorDoctors);

        SceneTool.switchToHospital("show", getAuthUser(), hospital);
        SceneTool.closeWindow(hospitalsListView);
    }

    public void addDoctorsHospital(ListView doctorsListView, List<Model> doctors) {
        doctorsListView
            .getSelectionModel()
            .getSelectedIndices()
            .stream()
            .map(doctorInputIndex -> (Doctor) doctors.get(doctorInputIndex))
            .forEach(doctor -> {
                (new Model())
                    .setTableName("hospitals_doctors")
                    .insert(newHospitalDoctorData(hospital, doctor, 0));
            });
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
