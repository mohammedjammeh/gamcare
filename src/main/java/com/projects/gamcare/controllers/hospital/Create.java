package com.projects.gamcare.controllers.hospital;

import com.projects.gamcare.models.Hospital;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class Create extends Hospital implements Initializable {
    @Override
    public void initialize(URL var1, ResourceBundle var2) {
        hideErrorBox();

//        leadDoctorChoiceBox
        addItemsFromTableToChoiceBox("regions", regionChoiceBox);
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
