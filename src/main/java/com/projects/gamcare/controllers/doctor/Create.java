package com.projects.gamcare.controllers.doctor;

import com.projects.gamcare.core.SceneTool;
import com.projects.gamcare.models.Doctor;
import com.projects.gamcare.models.Hospital;
import com.projects.gamcare.models.User;
import com.projects.gamcare.models.main.Model;
import javafx.fxml.FXML;
import java.time.LocalDate;
import java.util.List;

public class Create extends CreateFields {
    @FXML
    protected void onAddDoctorsButtonClick() {
        List<Integer> studentDoctorsIndices = getStudentDoctorsIndices();
        List<Integer> juniorDoctorsIndices = getJuniorDoctorsIndices();
        List<Integer> seniorDoctorsIndices = getSeniorDoctorsIndices();
    }


    @FXML
    protected void onCreateDoctorButtonClick() {
        (new Doctor()).insert(newDoctorData());

        Doctor newDoctor = getNewDoctor();

        (new User()).insert(newUserData(newDoctor));

        for (Integer hospitalInputIndex : hospitalsIndicesInput()) {
            Hospital hospital = (Hospital) hospitals.get(hospitalInputIndex);
            (new Model())
                .setTableName("hospitals_doctors")
                .insert(newHospitalDoctorData(hospital, newDoctor, 0));
        }

        SceneTool.switchToProfile("doctor/show", getAuthUser(), newDoctor);
    }

    private Doctor getNewDoctor() {
        return (Doctor) (new Doctor())
            .with("users")
            .where("school", universityInput())
            .where("field_of_study", fieldOfStudyInput())
            .where("career_level", careerLevelInput())
            .where("specialities_id", specialities.get(specialityIndexInput()).idAttribute())
            .first();
    }
}
