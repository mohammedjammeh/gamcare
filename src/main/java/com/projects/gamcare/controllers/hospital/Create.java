package com.projects.gamcare.controllers.hospital;

import com.projects.gamcare.wrappers.ChoiceBox;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
        String name = nameInput();
        String size = sizeInput();
        Integer leadDoctor = leadDoctorIndexInput();

        String emailAddress = emailAddressInput();
        String phoneNumber = phoneNumberInput();
        String relevantLink = relevantLinkInput();

        String compoundName = compoundNameInput();
        String town = townInput();
        Integer regionIndex = regionIndexInput();

        String otherDetails = otherDetailsInput();
    }
}
