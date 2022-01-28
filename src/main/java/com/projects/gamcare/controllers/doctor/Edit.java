
package com.projects.gamcare.controllers.doctor;

import com.projects.gamcare.controllers.doctor.parents.Fields;
import com.projects.gamcare.core.SceneTool;
import com.projects.gamcare.models.Doctor;
import com.projects.gamcare.models.Hospital;
import com.projects.gamcare.models.User;
import com.projects.gamcare.models.main.Model;
import com.projects.gamcare.models.main.ProfileUser;
import javafx.fxml.FXML;

public class Edit extends Fields {
    private ProfileUser profileUser;

    public void setUpEditForm() {
        setUpForm();

        setInputData(profileUser);
    }

    @FXML
    protected void onUpdateDoctorButtonClick() {
//        profileUser.getDoctor().update(getInputData());

//        (new Doctor()).insert(newDoctorData());
//
//        Doctor newDoctor = getDoctorFromDB();
//
//        (new User()).insert(
//            newUserData(newDoctorUserData(newDoctor))
//        );
//
//        for (Integer hospitalInputIndex : hospitalsIndicesInput()) {
//            Hospital hospital = (Hospital) hospitals.get(hospitalInputIndex);
//            (new Model())
//                .setTableName("hospitals_doctors")
//                .insert(newHospitalDoctorData(hospital, newDoctor, 0));
//        }
//
//        SceneTool.switchToProfile("doctor/show", getAuthUser(), getNewDoctorWithUser());
//        SceneTool.closeWindow(hospitalsListView);
    }

    /**
     * Getters & Setters
     */
    public void setProfileUser(ProfileUser profileUser) {
        this.profileUser = profileUser;
    }
}
