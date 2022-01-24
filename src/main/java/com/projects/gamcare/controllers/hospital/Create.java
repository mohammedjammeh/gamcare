package com.projects.gamcare.controllers.hospital;

import com.projects.gamcare.core.SceneTool;
import com.projects.gamcare.core.TimeTool;
import com.projects.gamcare.models.Doctor;
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
        Doctor leadDoctor = (Doctor) leadDoctors.get(leadDoctorIndexInput());

        (new Model())
            .setTableName("hospitals_doctors")
            .insert(newHospitalDoctorData(newHospital, leadDoctor, 1));

        SceneTool.switchToHospitalShow(getAuthUser(), newHospital);
    }

    protected Hospital getNewHospital() {
        return (Hospital) (new Hospital()).where("email_address", emailAddressInput()).first();
    }
}
