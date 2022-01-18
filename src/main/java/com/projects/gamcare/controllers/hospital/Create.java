package com.projects.gamcare.controllers.hospital;

import com.projects.gamcare.core.TimeTool;
import com.projects.gamcare.models.Hospital;
import javafx.fxml.FXML;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Create extends CreateFields {
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
        Integer leadDoctorIndex = leadDoctorIndexInput();

        (new Hospital())
            .getDatabase()
            .fields(fieldsList())
            .values(valuesList())
            .insert();
    }

    private List<Object> fieldsList() {
        return List.of(
            "name", "size",
            "email_address", "phone_number", "relevant_link",
            "compound", "town", "regions_id",
            "created_at", "updated_at", "other_details"
        );
    }

    private List<Object> valuesList() {
        String newDate = TimeTool.newDate();
        Integer regionId = regionIndexInput() + 1;

        return List.of(
            nameInput(), sizeInput(),
            emailAddressInput(), phoneNumberInput(), relevantLinkInput(),
            compoundNameInput(), townInput(), regionId,
            newDate, newDate, otherDetailsInput()
        );
    }
}
