package com.projects.gamcare.controllers.hospital;

import com.projects.gamcare.core.SceneTool;
import com.projects.gamcare.core.TimeTool;
import com.projects.gamcare.models.Hospital;
import com.projects.gamcare.models.main.Model;
import javafx.fxml.FXML;

import java.util.TreeMap;

public class Create extends CreateFields {
    @FXML
    protected void onCreateHospitalButtonClick() {
        (new Hospital())
            .insert(newHospitalData());

        Hospital newHospital = getNewHospital();

        (new Model())
            .setTableName("hospitals_doctors")
            .insert(newHospitalDoctorData(newHospital));

        SceneTool.switchToHospitalShow(getAuthUser(), newHospital);
    }

    protected Hospital getNewHospital() {
        return (Hospital) (new Hospital()).where("email_address", emailAddressInput()).first();
    }

    protected TreeMap<String, Object> newHospitalDoctorData(Hospital newHospital) {
        TreeMap<String, Object> data = new TreeMap<>();

        data.put("lead_doctor", 1);
        data.put("created_at", TimeTool.newDate());
        data.put("updated_at", TimeTool.newDate());
        data.put("doctors_id", leadDoctors.get(leadDoctorIndexInput()).idAttribute());
        data.put("hospitals_id", newHospital.idAttribute());

        return data;
    }
}
