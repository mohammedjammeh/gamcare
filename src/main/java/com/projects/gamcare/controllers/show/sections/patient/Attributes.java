package com.projects.gamcare.controllers.show.sections.patient;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Attributes {

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
    protected void onEditPatientButtonClick() {
        System.out.println("You can now edit patient profile.");
    }
}
