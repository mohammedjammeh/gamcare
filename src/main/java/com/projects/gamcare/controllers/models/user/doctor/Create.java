package com.projects.gamcare.controllers.models.user.doctor;

import com.projects.gamcare.controllers.models.user.CreateUser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class Create  extends CreateUser implements Initializable {
    @FXML
    private ListView<String> juniorDoctorsListView;

    @FXML
    private ListView<String> studentDoctorsListView;

    @FXML
    private ListView<String> seniorDoctorsListView;


    @FXML
    private TextField universityTextField;

    @FXML
    private TextField fieldOfStudyTextField;

    @FXML
    private ChoiceBox<String> careerLevelChoiceBox;


    @FXML
    private ChoiceBox<String> specialityChoiceBox;

    @FXML
    private ListView<String> hospitalsListView;



    @Override
    public void initialize(URL var1, ResourceBundle var2) {
        hideErrorBox();


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
        addItemsFromTableToChoiceBox("titles", titleChoiceBox);
        addItemsFromTableToChoiceBox("genders", genderChoiceBox);
        addItemsFromTableToChoiceBox("tribes", tribeChoiceBox);
        addItemsFromTableToChoiceBox("specialities", specialityChoiceBox);
        addItemsFromTableToChoiceBox("regions", regionChoiceBox);
        addItemsFromTableToListView("hospitals", hospitalsListView);
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
