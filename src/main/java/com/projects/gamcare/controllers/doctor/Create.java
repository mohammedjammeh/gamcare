package com.projects.gamcare.controllers.doctor;

import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class Create {
    @FXML
    private ListView<String> juniorDoctorsListView;

    @FXML
    private Label juniorDoctorsListViewLabel;

    @FXML
    private ListView<String> midLevelDoctorsListView;

    @FXML
    private Label midLevelDoctorsListViewLabel;

    @FXML
    private ListView<String> seniorDoctorsListView;

    @FXML
    private Label seniorDoctorsListViewLabel;


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
    private Label dateOfBirthFieldLabel;

    @FXML
    private DatePicker dateOfBirthPicker;



    @FXML
    private Label educationInstitutionFieldLabel;

    @FXML
    private TextField educationInstitutionTextField;

    @FXML
    private Label fieldOfStudyFieldLabel;

    @FXML
    private TextField fieldOfStudyTextField;

    @FXML
    private Label careerFieldLabel;



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
    private ListView<String> hospitalsListView;

    @FXML
    private Label hospitalsListViewLabel;



    @FXML
    private Label otherDetailsFieldLabel;

    @FXML
    private TextArea otherDetailsTextArea;




    @FXML void initialize() {
        errorBox.setVisible(false);
        errorBox.setManaged(false);

        juniorDoctorsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        juniorDoctorsListView.setOrientation(Orientation.VERTICAL);

        midLevelDoctorsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        midLevelDoctorsListView.setOrientation(Orientation.VERTICAL);

        seniorDoctorsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        seniorDoctorsListView.setOrientation(Orientation.VERTICAL);

        hospitalsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        hospitalsListView.setOrientation(Orientation.HORIZONTAL);
    }

    @FXML
    protected void onCreateDoctorButtonClick() {
        errorBox.setVisible(true);
        errorBox.setManaged(true);
        System.out.println("There is an error.");
    }
}
