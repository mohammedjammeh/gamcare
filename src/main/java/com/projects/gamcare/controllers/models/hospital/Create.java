package com.projects.gamcare.controllers.models.hospital;

import com.projects.gamcare.controllers.models.CreateModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class Create extends CreateModel implements Initializable {
    @FXML
    private TextField nameTextField;

    @FXML
    private ChoiceBox<String> sizeChoiceBox;

    @FXML
    private ChoiceBox<String> leadDoctorChoiceBox;


    @Override
    public void initialize(URL var1, ResourceBundle var2) {
        hideErrorBox();

//        leadDoctorChoiceBox
        addItemsFromTableToChoiceBox("regions", regionChoiceBox);
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
