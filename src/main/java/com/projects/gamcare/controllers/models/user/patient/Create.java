package com.projects.gamcare.controllers.models.user.patient;

import com.projects.gamcare.controllers.models.user.CreateUser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Create extends CreateUser implements Initializable {
    @FXML
    private ChoiceBox<String> hospitalChoiceBox;


    @FXML
    private TextField weightTextField;

    @FXML
    private TextField heightTextField;

    @FXML
    private ChoiceBox<String> bloodTypeChoiceBox;
    

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
        String firstName = firstNameTextField.getText();
        String middleName = middleNameTextField.getText();
        String lastName = lastNameTextField.getText();

        Integer titleIndex = titleChoiceBox.getSelectionModel().getSelectedIndex();
        Integer genderIndex = genderChoiceBox.getSelectionModel().getSelectedIndex();
        Integer tribeIndex = tribeChoiceBox.getSelectionModel().getSelectedIndex();

        Integer hospitalIndex = hospitalChoiceBox.getSelectionModel().getSelectedIndex();
        String placeOfBirth = placeOfBirthTextField.getText();
        LocalDate dateOfBirth = dateOfBirthPicker.getValue();

        String weight = weightTextField.getText();
        String height = heightTextField.getText();
        Integer bloodTypeIndex = bloodTypeChoiceBox.getSelectionModel().getSelectedIndex();

        String emailAddress = emailAddressTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        String relevantLink = relevantLinkTextField.getText();

        String compoundName = compoundNameTextField.getText();
        String town = townTextField.getText();
        Integer regionIndex = regionChoiceBox.getSelectionModel().getSelectedIndex();

        String otherDetails = otherDetailsTextArea.getText();

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
