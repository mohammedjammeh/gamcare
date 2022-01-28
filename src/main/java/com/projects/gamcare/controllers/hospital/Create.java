package com.projects.gamcare.controllers.hospital;

import com.projects.gamcare.controllers.hospital.parents.Fields;
import com.projects.gamcare.core.SceneTool;
import com.projects.gamcare.core.TimeTool;
import com.projects.gamcare.models.Doctor;
import com.projects.gamcare.models.Hospital;
import com.projects.gamcare.models.main.Model;
import javafx.fxml.FXML;

import java.util.TreeMap;

public class Create extends Fields {
    @FXML
    protected void onCreateHospitalButtonClick() {
        (new Hospital())
            .insert(getInputData());

        Hospital newHospital = getHospitalFromDB();
        Doctor leadDoctor = (Doctor) leadDoctors.get(leadDoctorIndexInput());

        (new Model())
            .setTableName("hospitals_doctors")
            .insert(newHospitalDoctorData(newHospital, leadDoctor, 1));

        SceneTool.switchToHospital("show", getAuthUser(), newHospital);
        SceneTool.closeWindow(nameTextField);
    }

    /**
     * Getters & Setters
     */
    public TreeMap<String, Object> getInputData() {
        TreeMap<String, Object> data = super.getInputData();

        data.put("created_at", TimeTool.newDate());

        return data;
    }
}
