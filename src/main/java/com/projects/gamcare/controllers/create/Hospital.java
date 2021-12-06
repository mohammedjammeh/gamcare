package com.projects.gamcare.controllers.create;

import com.projects.gamcare.fields.HospitalFields;
import javafx.fxml.FXML;

public class Hospital extends HospitalFields {
    public void initialize() {
        super.initialize();

//        leadDoctorChoiceBox
    }


    @FXML
    protected void onCreateHospitalButtonClick() {
        String name = getName();
        String size = getSize();
        Integer leadDoctor = getLeadDoctorIndex();

        String emailAddress = getEmailAddress();
        String phoneNumber = getPhoneNumber();
        String relevantLink = getRelevantLink();

        String compoundName = getCompoundName();
        String town = getTown();
        Integer regionIndex = getRegionIndex();

        String otherDetails = getOtherDetails();
    }
}
