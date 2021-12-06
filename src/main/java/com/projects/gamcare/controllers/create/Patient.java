package com.projects.gamcare.controllers.create;

import com.projects.gamcare.fields.PatientFields;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Patient extends PatientFields implements Initializable {
    @Override
    public void initialize(URL var1, ResourceBundle var2) {
        hideErrorBox();

        addItemsFromTableToChoiceBox("titles", titleChoiceBox);
        addItemsFromTableToChoiceBox("genders", genderChoiceBox);
        addItemsFromTableToChoiceBox("tribes", tribeChoiceBox);
        addItemsFromTableToChoiceBox("hospitals", hospitalChoiceBox);
        addItemsFromTableToChoiceBox("blood_types", bloodTypeChoiceBox);
        addItemsFromTableToChoiceBox("regions", regionChoiceBox);
    }


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

        byte[] salt = createSalt();
        String hash = generateHash("password123", "SHA-256", salt);
    }


    public static byte[] createSalt() {
        byte[] bytes = new byte[20];
        SecureRandom random = new SecureRandom();
        random.nextBytes(bytes);

        return bytes;
    }


    public static String generateHash(String password, String algorithm, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digest.reset();
        digest.update(salt);
        byte[] hash = digest.digest(password.getBytes());

        return bytesToHex(hash);
    }


    public static String bytesToHex(byte[] bytes) {
        byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }

        return new String(hexChars, StandardCharsets.UTF_8);
    }


    private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);
}
