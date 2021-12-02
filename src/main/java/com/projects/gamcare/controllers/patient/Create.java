package com.projects.gamcare.controllers.patient;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.sql.*;

public class Create implements Initializable {
    @FXML
    private VBox errorBox;


    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField middleNameTextField;

    @FXML
    private TextField lastNameTextField;


    @FXML
    private ChoiceBox<String> titleChoiceBox;

    @FXML
    private ChoiceBox<String> genderChoiceBox;

    @FXML
    private ChoiceBox<String> tribeChoiceBox;


    @FXML
    private ChoiceBox<String> hospitalChoiceBox;

    @FXML
    private TextField placeOfBirthTextField;

    @FXML
    private DatePicker dateOfBirthPicker;


    @FXML
    private TextField weightTextField;

    @FXML
    private TextField heightTextField;

    @FXML
    private ChoiceBox<String> bloodTypeChoiceBox;


    @FXML
    private TextField emailAddressTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField relevantLinkTextField;


    @FXML
    private TextField compoundNameTextField;

    @FXML
    private TextField townTextField;

    @FXML
    private ChoiceBox<String> regionChoiceBox;


    @FXML
    private TextArea otherDetailsTextArea;
    

    @Override
    public void initialize(URL var1, ResourceBundle var2) {
        errorBox.setVisible(false);
        errorBox.setManaged(false);


        addItemsFromTableToChoiceBox("titles", titleChoiceBox);
        addItemsFromTableToChoiceBox("genders", genderChoiceBox);
        addItemsFromTableToChoiceBox("tribes", tribeChoiceBox);
        addItemsFromTableToChoiceBox("hospitals", hospitalChoiceBox);
        addItemsFromTableToChoiceBox("blood_types", bloodTypeChoiceBox);
        addItemsFromTableToChoiceBox("regions", regionChoiceBox);
    }

    public static void addItemsFromTableToChoiceBox(String table, ChoiceBox<String> choiceBox) {
        try {
            Connection dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamcare", "root", "3aj3!96wMWeyU9&z");

            PreparedStatement statement = dbConnection.prepareStatement("SELECT name FROM " + table + " ORDER BY id");
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                String title = capitalizeString(results.getString("name"));
                choiceBox.getItems().add(title);

                if(results.isFirst())
                    choiceBox.setValue(title);
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }


    public static String capitalizeString(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
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
