package com.projects.gamcare.controllers.patient;

import com.projects.gamcare.controllers.ShowParent;
import com.projects.gamcare.models.Doctor;
import com.projects.gamcare.models.Patient;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class Show extends ShowParent {
    private Patient patient;

    @FXML
    protected void onEditPatientButtonClick() {
        System.out.println("You can now edit patient profile.");
    }

    public void setUpBody() {
        patient = getProfileUser().getPatient();

        buildAttributesSection();
        buildOtherDetailsSection(getProfileUser());
    }

    private void buildAttributesSection() {
        profileAttributes.getChildren().addAll(
            patientTopAttributesRow(),
            nameAttributesRow(getProfileUser()),
            birthAttributesRow(),
            patientMainAttributesRow(),
            contactAttributesRow(getProfileUser()),
            addressAttributesRow(getProfileUser())
        );
    }

    private HBox patientTopAttributesRow() {
        HBox topRow = new HBox();
        ObservableList<Node> topRowChildren = topRow.getChildren();

        topRowChildren.addAll(attributeBoxWithSpacer("Title:", getProfileUser().getTitle().nameAttribute()));
        topRowChildren.addAll(attributeBoxWithSpacer("Gender:", getProfileUser().getGender().nameAttribute()));
        topRowChildren.add(attributeBox("Tribe:", getProfileUser().getTribe().nameAttribute()));

        return topRow;
    }

    private HBox birthAttributesRow() {
        HBox birthRow = new HBox();
        ObservableList<Node> birthRowChildren = birthRow.getChildren();

        birthRowChildren.addAll(attributeBoxWithSpacer("Date of birth:", getProfileUser().dateOfBirthDisplay()));
        birthRowChildren.addAll(attributeBoxWithSpacer("Place of birth:", getProfileUser().getAttribute("place_of_birth")));
        birthRowChildren.add(attributeBox("Hospital:", patient.getHospital().nameAttribute()));

        return birthRow;
    }

    private HBox patientMainAttributesRow() {
        HBox mainRow = new HBox();
        ObservableList<Node> mainRowChildren = mainRow.getChildren();

        mainRowChildren.addAll(attributeBoxWithSpacer("Blood type:", patient.getBloodType().nameAttribute()));
        mainRowChildren.addAll(attributeBoxWithSpacer("Weight:", patient.weightAttribute()));
        mainRowChildren.add(attributeBox("Height:", patient.heightAttribute()));

        return mainRow;
    }
}
