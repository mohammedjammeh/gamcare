package com.projects.gamcare.controllers.patient;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Create {
    @FXML
    private VBox errorBox;

    @FXML
    private Label errorLabel;



    @FXML
    private Label firstNameFieldLabel;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private Label middleNameFieldLabel;

    @FXML
    private TextField middleNameTextField;

    @FXML
    private Label lastNameFieldLabel;

    @FXML
    private TextField lastNameTextField;



    @FXML
    private Label titleFieldLabel;

    @FXML
    private Label genderFieldLabel;

    @FXML
    private Label tribeFieldLabel;




    @FXML
    private Label dateOfBirthFieldLabel;

    @FXML
    private DatePicker dateOfBirthPicker;

    @FXML
    private Label placeOfBirthFieldLabel;

    @FXML
    private TextField placeOfBirthTextField;

    @FXML
    private Label currentHospitalLabel;



    @FXML
    private Label weightFieldLabel;

    @FXML
    private TextField weightTextField;

    @FXML
    private Label heightFieldLabel;

    @FXML
    private TextField heightTextField;

    @FXML
    private Label bloodTypeFieldLabel;



    @FXML
    private Label emailFieldLabel;

    @FXML
    private TextField emailTextField;

    @FXML
    private Label phoneNumberFieldLabel;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private Label relevantLinkFieldLabel;

    @FXML
    private TextField relevantLinkTextField;




    @FXML
    private Label compoundNameFieldLabel;

    @FXML
    private TextField compoundNameTextField;

    @FXML
    private Label townFieldLabel;

    @FXML
    private TextField townTextField;

    @FXML
    private Label regionFieldLabel;



    @FXML
    private Label otherDetailsFieldLabel;

    @FXML
    private TextArea otherDetailsTextArea;



    @FXML
    private Button createPatientButton;



    @FXML void initialize() {
        errorBox.setVisible(false);
        errorBox.setManaged(false);
    }

    @FXML
    protected void onCreatePatientButtonClick() throws NoSuchAlgorithmException {


        String data = "Hello World";
        String algorithm = "SHA-256";
        byte[] salt = createSalt();

        System.out.println(generateHash(data, algorithm, salt));
    }

    public static byte[] createSalt() {
        byte[] bytes = new byte[20];
        SecureRandom random = new SecureRandom();
        random.nextBytes(bytes);

        return bytes;
    }

    public static String generateHash(String data, String algorithm, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digest.reset();
        digest.update(salt);
        byte[] hash = digest.digest(data.getBytes());

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
