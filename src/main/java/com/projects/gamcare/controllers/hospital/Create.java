package com.projects.gamcare.controllers.hospital;

import com.projects.gamcare.core.SceneTool;
import com.projects.gamcare.core.TimeTool;
import com.projects.gamcare.models.Hospital;
import com.projects.gamcare.models.main.Model;
import javafx.fxml.FXML;

import java.util.*;

public class Create extends CreateFields {
    private Hospital newHospital;

    @FXML
    protected void onCreateHospitalButtonClick() {
        (new Hospital())
            .insert(newHospitalData());

        setNewHospital();

        (new Model())
            .setTableName("hospitals_doctors")
            .insert(newHospitalDoctorData());

        SceneTool.switchToHospitalShow(getAuthUser(), newHospital);
    }

    private TreeMap<String, Object> newHospitalData() {
        TreeMap<String, Object> data = new TreeMap<>();

        data.put("name", nameInput());
        data.put("size", sizeInput());
        data.put("email_address", emailAddressInput());
        data.put("phone_number", phoneNumberInput());
        data.put("relevant_link", relevantLinkInput());
        data.put("compound", compoundNameInput());
        data.put("town", townInput());
        data.put("regions_id", regions.get(regionIndexInput()).idAttribute());
        data.put("created_at", TimeTool.newDate());
        data.put("updated_at", TimeTool.newDate());
        data.put("other_details", otherDetailsInput());

        return data;
    }

    private TreeMap<String, Object> newHospitalDoctorData() {
        TreeMap<String, Object> data = new TreeMap<>();

        data.put("lead_doctor", 1);
        data.put("created_at", TimeTool.newDate());
        data.put("updated_at", TimeTool.newDate());
        data.put("doctors_id", leadDoctors.get(leadDoctorIndexInput()).idAttribute());
        data.put("hospitals_id", newHospital.idAttribute());

        return data;
    }

    private void setNewHospital() {
        newHospital = (Hospital) (new Hospital()).where("email_address", emailAddressInput()).first();
    }
}
