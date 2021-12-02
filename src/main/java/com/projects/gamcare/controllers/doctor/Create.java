package com.projects.gamcare.controllers.doctor;

import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

public class Create {
    @FXML
    private VBox errorBox;


    @FXML
    private ListView<String> juniorDoctorsListView;

    @FXML
    private ListView<String> studentDoctorsListView;

    @FXML
    private ListView<String> seniorDoctorsListView;


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
    private TextField universityTextField;

    @FXML
    private TextField fieldOfStudyTextField;

    @FXML
    private ChoiceBox<String> careerLevelChoiceBox;


    @FXML
    private ChoiceBox<String> specialityChoiceBox;

    @FXML
    private TextField placeOfBirthTextField;

    @FXML
    private DatePicker dateOfBirthPicker;


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
    private ListView<String> hospitalsListView;


    @FXML
    private TextArea otherDetailsTextArea;


    @FXML void initialize() {
        errorBox.setVisible(false);
        errorBox.setManaged(false);



        studentDoctorsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        studentDoctorsListView.setOrientation(Orientation.VERTICAL);

        juniorDoctorsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        juniorDoctorsListView.setOrientation(Orientation.VERTICAL);

        seniorDoctorsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        seniorDoctorsListView.setOrientation(Orientation.VERTICAL);

        hospitalsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        hospitalsListView.setOrientation(Orientation.HORIZONTAL);



//        studentDoctorsListView
//        juniorDoctorsListView
//        seniorDoctorsListView
//        hospitalsListView

        addItemsFromTableToChoiceBox("titles", titleChoiceBox);
        addItemsFromTableToChoiceBox("genders", genderChoiceBox);
        addItemsFromTableToChoiceBox("tribes", tribeChoiceBox);
        addItemsFromTableToChoiceBox("specialities", specialityChoiceBox);
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
    protected void onAddDoctorsButtonClick() {
        List<Integer> juniorDoctorsIndices = juniorDoctorsListView.getSelectionModel().getSelectedIndices();
        List<Integer> midLevelDoctorsIndices = studentDoctorsListView.getSelectionModel().getSelectedIndices();
        List<Integer> seniorDoctorsIndices = seniorDoctorsListView.getSelectionModel().getSelectedIndices();
    }


    @FXML
    protected void onCreateDoctorButtonClick() {
        String firstName = firstNameTextField.getText();
        String middleName = middleNameTextField.getText();
        String lastName = lastNameTextField.getText();

        Integer titleIndex = titleChoiceBox.getSelectionModel().getSelectedIndex();
        Integer genderIndex = genderChoiceBox.getSelectionModel().getSelectedIndex();
        Integer tribeIndex = tribeChoiceBox.getSelectionModel().getSelectedIndex();

        String university = universityTextField.getText();
        String fieldOfStudy = fieldOfStudyTextField.getText();
        String careerLevel = careerLevelChoiceBox.getValue();

        Integer specialityIndex = specialityChoiceBox.getSelectionModel().getSelectedIndex();
        String placeOfBirth = placeOfBirthTextField.getText();
        LocalDate dateOfBirth = dateOfBirthPicker.getValue();

        String emailAddress = emailAddressTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        String relevantLink = relevantLinkTextField.getText();

        String compoundName = compoundNameTextField.getText();
        String town = townTextField.getText();
        String region = regionChoiceBox.getValue();

        String otherDetails = otherDetailsTextArea.getText();
    }
}
