package com.projects.gamcare.controllers.hospital;

import com.projects.gamcare.fields.HospitalFields;
import javafx.fxml.FXML;

public class Create extends HospitalFields {
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
