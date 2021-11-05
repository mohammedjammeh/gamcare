package com.projects.gamcare.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Patient {
    @FXML
    private Label firstNameFieldLabel;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private Label lastNameFieldLabel;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private Label emailFieldLabel;

    @FXML
    private TextField emailTextField;

    @FXML
    private Label dateOfBirthFieldLabel;

    @FXML
    private DatePicker dateOfBirthPicker;

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
    private Label addressDescriptionFieldLabel;

    @FXML
    private TextArea addressDescriptionTextArea;

    @FXML
    protected void onHospitalsButtonClick() {
        System.out.println("You are now looking at hospitals.");
    }

    @FXML
    protected void onMyProfileButtonClick() {
        System.out.println("You are now looking at your profile.");
    }

    @FXML
    protected void onLogOutButtonClick() {
        System.out.println("You are now logged out.");
    }

    @FXML
    protected void onCreatePatientButtonClick() {
        System.out.println("You have now created a patient.");
    }
}
