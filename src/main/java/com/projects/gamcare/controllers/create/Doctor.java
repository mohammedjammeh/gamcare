package com.projects.gamcare.controllers.create;

import com.projects.gamcare.fields.DoctorFields;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class Doctor extends DoctorFields implements Initializable {

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
        String firstName = getFirstName();
        String middleName = getMiddleName();
        String lastName = getLastName();

        Integer titleIndex = getTitleIndex();
        Integer genderIndex = getGenderIndex();
        Integer tribeIndex = getTribeIndex();

        String university = getUniversity();
        String fieldOfStudy = getFieldOfStudy();
        String careerLevel = getCareerLevel();

        Integer specialityIndex = getSpecialityIndex();
        String placeOfBirth = getPlaceOfBirth();
        LocalDate dateOfBirth = getDateOfBirth();

        String emailAddress = getEmailAddress();
        String phoneNumber = getPhoneNumber();
        String relevantLink = getRelevantLink();

        String compoundName = getCompoundName();
        String town = getTown();
        Integer regionIndex = getRegionIndex();

        String otherDetails = getOtherDetails();
    }
}
