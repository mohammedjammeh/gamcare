
package com.projects.gamcare.controllers.doctor;

import com.projects.gamcare.controllers.doctor.parents.Fields;
import com.projects.gamcare.core.SceneTool;
import com.projects.gamcare.models.Doctor;
import com.projects.gamcare.models.Hospital;
import com.projects.gamcare.models.User;
import com.projects.gamcare.models.main.Model;
import com.projects.gamcare.models.main.ProfileUser;
import javafx.fxml.FXML;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Edit extends Fields {
    private ProfileUser profileUser;

    public void setUpEditForm() {
        setUpForm();
        setInputData(profileUser);
    }

    @FXML
    protected void onUpdateDoctorButtonClick() {
        Doctor doctor = profileUser.getDoctor();
        User user = profileUser.getDoctorUser();
        List<Integer> doctorHospitalsIndices = doctor.getHospitals()
            .stream().map(hospitalID -> hospitals.indexOf(hospitalID))
            .toList();

        doctor.update(getInputData());
        user.update(newUserData(newDoctorUserData(doctor)));


        final Predicate<Integer> notInDoctorHospitalIndices = hospitalInputIndex -> ! doctorHospitalsIndices.contains(hospitalInputIndex);
        hospitalsIndicesInput()
            .stream()
            .filter(notInDoctorHospitalIndices)
            .forEach(hospitalInputIndex -> {
                Hospital hospital = (Hospital) hospitals.get(hospitalInputIndex);
                (new Model())
                    .setTableName("hospitals_doctors")
                    .insert(newHospitalDoctorData(hospital, doctor, 0));
            });


        final Predicate<Integer> notInHospitalsIndicesInput = hospitalInputIndex -> ! hospitalsIndicesInput().contains(hospitalInputIndex);
        List<Map<String, Object>> doctorHospitalsToRemove = doctorHospitalsIndices
            .stream()
            .filter(notInHospitalsIndicesInput)
            .map(hospitalInputIndex -> doctorHospitalInputRowsValues(doctor, hospitalInputIndex))
            .toList();

        if(! doctorHospitalsToRemove.isEmpty()) {
            (new Model())
                .setTableName("hospitals_doctors")
                .deleteManyWhere(doctorHospitalsToRemove);
        }


        SceneTool.switchToProfile("doctor/show", getAuthUser(), getNewDoctorWithUser());
        SceneTool.closeWindow(hospitalsListView);
    }

    private Map<String, Object> doctorHospitalInputRowsValues(Doctor doctor, Integer hospitalInputIndex) {
        return doctorHospitalRowValues(
            doctor,
            (Hospital) hospitals.get(hospitalInputIndex)
        );
    }

    /**
     * Getters & Setters
     */
    public void setProfileUser(ProfileUser profileUser) {
        this.profileUser = profileUser;
    }
}
