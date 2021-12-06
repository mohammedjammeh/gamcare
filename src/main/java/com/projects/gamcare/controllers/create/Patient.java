package com.projects.gamcare.controllers.create;

import com.projects.gamcare.core.Hash;
import com.projects.gamcare.fields.PatientFields;
import javafx.fxml.FXML;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;

public class Patient extends PatientFields {
    @FXML
    protected void onCreatePatientButtonClick() throws NoSuchAlgorithmException {
        String firstName = getFirstName();
        String middleName = getMiddleName();
        String lastName = getLastName();

        Integer titleIndex = getTitleIndex();
        Integer genderIndex = getGenderIndex();
        Integer tribeIndex = getTribeIndex();

        Integer hospitalIndex = getHospitalIndex();
        String placeOfBirth = getPlaceOfBirth();
        LocalDate dateOfBirth = getDateOfBirth();

        String weight = getWeight();
        String height = getHeight();
        Integer bloodTypeIndex = getBloodTypeIndex();

        String emailAddress = getEmailAddress();
        String phoneNumber = getPhoneNumber();
        String relevantLink = getRelevantLink();

        String compoundName = getCompoundName();
        String town = getTown();
        Integer regionIndex = getRegionIndex();

        String otherDetails = getOtherDetails();

        byte[] salt = Hash.createSalt();
        String hash = Hash.generate("password123", salt);
    }
}
