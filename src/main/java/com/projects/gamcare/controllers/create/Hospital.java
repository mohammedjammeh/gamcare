package com.projects.gamcare.controllers.create;

import com.projects.gamcare.models.HospitalModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class Hospital extends HospitalModel implements Initializable {
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
