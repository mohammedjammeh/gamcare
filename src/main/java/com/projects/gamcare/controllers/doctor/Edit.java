
package com.projects.gamcare.controllers.doctor;

import com.projects.gamcare.controllers.doctor.parents.Fields;
import com.projects.gamcare.core.SceneTool;
import com.projects.gamcare.models.Doctor;
import com.projects.gamcare.models.Hospital;
import com.projects.gamcare.models.User;
import com.projects.gamcare.models.main.Model;
import com.projects.gamcare.models.main.ProfileUser;
import javafx.fxml.FXML;

import javax.print.Doc;
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


        final Predicate<Integer> notInDoctorHospitalIndices = inputIndex -> ! doctorHospitalsIndices.contains(inputIndex);
        hospitalsIndicesInput()
            .stream()
            .filter(notInDoctorHospitalIndices)
            .forEach(inputIndex -> {
                Hospital hospital = (Hospital) hospitals.get(inputIndex);
                (new Model())
                    .setTableName("hospitals_doctors")
                    .insert(newHospitalDoctorData(hospital, doctor, 0));
            });


        final Predicate<Integer> notInHospitalsIndicesInput = inputIndex -> ! hospitalsIndicesInput().contains(inputIndex);
        List<Map<String, Object>> doctorHospitalsToRemove = doctorHospitalsIndices
            .stream()
            .filter(notInHospitalsIndicesInput)
            .map(inputIndex -> doctorsHospitalsRowValues(doctor, inputIndex))
            .toList();

        if(! doctorHospitalsToRemove.isEmpty()) {
            (new Model())
                .setTableName("hospitals_doctors")
                .deleteManyWhere(doctorHospitalsToRemove);
        }


        SceneTool.switchToProfile("doctor/show", getAuthUser(), getNewDoctorWithUser());
        SceneTool.closeWindow(hospitalsListView);
    }

    private Map<String, Object> doctorsHospitalsRowValues(Doctor doctor, Integer inputIndex) {
        Hospital hospital = (Hospital) hospitals.get(inputIndex);

        Map<String, Object> rowValues = new HashMap<>();

        rowValues.put("doctors_id", doctor.idAttribute());
        rowValues.put("hospitals_id", hospital.idAttribute());

        return rowValues;
    }

    /**
     * Getters & Setters
     */
    public void setProfileUser(ProfileUser profileUser) {
        this.profileUser = profileUser;
    }
}
