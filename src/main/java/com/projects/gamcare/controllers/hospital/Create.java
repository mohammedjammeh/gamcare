package com.projects.gamcare.controllers.hospital;

import javafx.fxml.FXML;
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
    private Label nameFieldLabel;

    @FXML
    private TextField nameTextField;

    @FXML
    private Label sizeFieldLabel;

    @FXML
    private Label leadDoctorFieldLabel;


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
    protected void onCreateHospitalButtonClick() {
        errorBox.setVisible(true);
        errorBox.setManaged(true);
        System.out.println("There is an error.");
    }
}
