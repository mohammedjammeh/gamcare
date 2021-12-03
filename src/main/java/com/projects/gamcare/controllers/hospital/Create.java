package com.projects.gamcare.controllers.hospital;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class Create {
    @FXML
    private VBox errorBox;


    @FXML
    private TextField nameTextField;

    @FXML
    private ChoiceBox<String> sizeChoiceBox;

    @FXML
    private ChoiceBox<String> leadDoctorChoiceBox;


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


    @FXML void initialize() {
        errorBox.setVisible(false);
        errorBox.setManaged(false);

//        leadDoctorChoiceBox
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
    protected void onCreateHospitalButtonClick() {
        String name = nameTextField.getText();
        String size = sizeChoiceBox.getValue();
        Integer leadDoctor = leadDoctorChoiceBox.getSelectionModel().getSelectedIndex();

        String emailAddress = emailAddressTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        String relevantLink = relevantLinkTextField.getText();

        String compoundName = compoundNameTextField.getText();
        String town = townTextField.getText();
        Integer regionIndex = regionChoiceBox.getSelectionModel().getSelectedIndex();

        String otherDetails = otherDetailsTextArea.getText();
    }
}
