package com.projects.gamcare.controllers.hospital;

import com.projects.gamcare.controllers.hospital.parents.Fields;
import com.projects.gamcare.core.SceneTool;
import com.projects.gamcare.models.Doctor;
import com.projects.gamcare.models.Hospital;
import com.projects.gamcare.models.main.Model;
import javafx.fxml.FXML;

public class Create extends Fields {
    @FXML
    protected void onCreateHospitalButtonClick() {
        (new Hospital())
            .insert(getInputData());

        Hospital newHospital = getNewHospital();
        Doctor leadDoctor = (Doctor) leadDoctors.get(leadDoctorIndexInput());

        (new Model())
            .setTableName("hospitals_doctors")
            .insert(newHospitalDoctorData(newHospital, leadDoctor, 1));

        SceneTool.switchToHospitalShow(getAuthUser(), newHospital);
        SceneTool.closeWindow(nameTextField);
    }

    protected Hospital getNewHospital() {
        return (Hospital) (new Hospital()).where("email_address", emailAddressInput()).first();
    }
}
