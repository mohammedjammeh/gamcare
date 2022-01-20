package com.projects.gamcare.controllers.hospital;

import com.projects.gamcare.core.SceneTool;
import com.projects.gamcare.core.TimeTool;
import com.projects.gamcare.models.Hospital;
import com.projects.gamcare.models.main.Model;
import javafx.fxml.FXML;

import java.util.List;

public class Create extends CreateFields {
    private Hospital newHospital;

    public void initialize() {
        super.initialize();
        nameTextField.setText("Badibu");
        emailAddressTextField.setText("mohammedjamme@yahoo.com");
        phoneNumberTextField.setText("07506259330");
        relevantLinkTextField.setText("https://www.google.co.uk/");
        compoundNameTextField.setText("Jammeh Kunda");
        townTextField.setText("Santoto");
    }

    @FXML
    protected void onCreateHospitalButtonClick() {
        (new Hospital())
            .getDatabase()
            .fields(hospitalFieldsList())
            .values(hospitalValuesList())
            .insert();

        setNewHospital();

        (new Model())
            .setTableName("hospitals_doctors")
            .getDatabase()
            .fields(hospitalDoctorFieldsList())
            .values(hospitalDoctorValuesList())
            .insert();

        SceneTool.switchToHospitalShow(getAuthUser(), newHospital);
    }

    private void setNewHospital() {
        newHospital = (Hospital) (new Hospital()).where("email_address", emailAddressInput()).first();
    }

    private List<Object> hospitalFieldsList() {
        return List.of(
            "name", "size",
            "email_address", "phone_number", "relevant_link",
            "compound", "town", "regions_id",
            "created_at", "updated_at", "other_details"
        );
    }

    private List<Object> hospitalValuesList() {
        String newDate = TimeTool.newDate();
        Integer regionId = regionIndexInput() + 1;

        return List.of(
            nameInput(), sizeInput(),
            emailAddressInput(), phoneNumberInput(), relevantLinkInput(),
            compoundNameInput(), townInput(), regionId,
            newDate, newDate, otherDetailsInput()
        );
    }

    private List<Object> hospitalDoctorFieldsList() {
        return List.of(
            "lead_doctor", "created_at", "updated_at",
            "doctors_id", "hospitals_id"
        );
    }

    private List<Object> hospitalDoctorValuesList() {
        String newDate = TimeTool.newDate();
        Integer doctorId = leadDoctors.get(leadDoctorIndexInput()).idAttribute();

        return List.of(
            1, newDate, newDate,
            doctorId, newHospital.idAttribute()
        );
    }
}
