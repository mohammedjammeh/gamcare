package com.projects.gamcare.controllers.patient;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

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



    @FXML void initialize() {
        errorBox.setVisible(false);
        errorBox.setManaged(false);
    }

    @FXML
    protected void onCreatePatientButtonClick() {
        errorBox.setVisible(true);
        errorBox.setManaged(true);
        System.out.println("There is an error.");
    }
}
