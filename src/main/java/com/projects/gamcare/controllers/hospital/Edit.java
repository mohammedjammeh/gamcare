package com.projects.gamcare.controllers.hospital;

import com.projects.gamcare.controllers.hospital.parents.Fields;
import com.projects.gamcare.core.SceneTool;
import com.projects.gamcare.models.Doctor;
import com.projects.gamcare.models.Hospital;
import com.projects.gamcare.models.main.Model;
import javafx.fxml.FXML;

public class Edit extends Fields {
    private Hospital hospital;

    @FXML
    protected void onUpdateHospitalButtonClick() {
        hospital.update(getInputData());
        hospital = getHospitalFromDB();

        Doctor currentLeadDoctor = hospital.getLeadDoctor();
        Doctor selectedLeadDoctor = (Doctor) leadDoctors.get(leadDoctorIndexInput());

        if (currentLeadDoctor.equals(selectedLeadDoctor) ) {
            return;
        }

        Model hospitalLeadDoctorPivot = (new Model())
            .setTableName("hospitals_doctors")
            .where("lead_doctor", 1)
            .where("doctors_id", currentLeadDoctor.idAttribute())
            .where("hospitals_id", hospital.idAttribute())
            .first();

        hospitalLeadDoctorPivot
            .update(newHospitalDoctorData(hospital, selectedLeadDoctor, 1));

        SceneTool.switchToHospitalShow(getAuthUser(), hospital);
        SceneTool.closeWindow(nameTextField);
    }

    /**
     * Getters & Setters
     */
    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
}
