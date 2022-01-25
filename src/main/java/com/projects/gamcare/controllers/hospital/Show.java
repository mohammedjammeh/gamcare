package com.projects.gamcare.controllers.hospital;

import com.projects.gamcare.controllers.ShowParent;
import com.projects.gamcare.core.SceneTool;
import com.projects.gamcare.models.Doctor;
import com.projects.gamcare.models.Hospital;
import com.projects.gamcare.models.Patient;
import com.projects.gamcare.models.User;
import com.projects.gamcare.models.main.Model;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.*;

public class Show extends ShowParent {
    private Hospital hospital;

    @FXML
    protected VBox profilePatients, profileDoctors;

    @FXML
    protected Button editHospitalButton, editHospitalDetailsButton, addPatientButton, addDoctorButton;

    @FXML
    protected void onEditHospitalButtonClick() {
        System.out.println("You have now edit a hospital.");
    }

    @FXML
    protected void onAddPatientButtonClick() {
        System.out.println("You have now added a patient.");
    }

    @FXML
    protected void onAddDoctorButtonClick() {
        SceneTool.switchToUserCreate("doctor/create", getAuthUser(), hospital);
    }

    @FXML
    protected void onShowPatientButtonClick(Patient patient) {
        SceneTool.switchToProfile("patient/show", getAuthUser(), patient);
    }

    /**
     * Getters & Setters
     */
    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setUpBody() {
        updateButtonsVisibility();
        buildAttributesSection();
        buildOtherDetailsSection(getHospital());
        buildPatientsSection();
        buildDoctorsSection(getHospital().getDoctors(), profileDoctors);
    }

    /**
     * Build Section
     */
    private void updateButtonsVisibility() {
        if (getAuthUser().isNotAdmin()) {
            hide(editHospitalButton);
            hide(editHospitalDetailsButton);
            hide(addDoctorButton);
        }
    }

    private void buildAttributesSection() {
        profileAttributes.getChildren().addAll(
            hospitalTopAttributesRow(),
            contactAttributesRow(getHospital()),
            addressAttributesRow(getHospital())
        );
    }

    private HBox hospitalTopAttributesRow() {
        HBox topRow = new HBox();
        ObservableList<Node> topRowChildren = topRow.getChildren();

        topRowChildren.addAll(attributeBoxWithSpacer("Name:", getHospital().getAttribute("name")));
        topRowChildren.addAll(attributeBoxWithSpacer("Size:", getHospital().getAttribute("size")));
        topRowChildren.add(attributeBox("Lead doctor:", getHospital().getLeadDoctor().fullNameAttribute()));

        return topRow;
    }

    private void buildPatientsSection() {
        for (Model patientModel: getHospital().getPatients()) {
            Patient patient = (Patient) patientModel;
            HBox tableBody = newHBoxWithStyleClass("tableBody");

            Map<String, Node> tableFirstName = tableLabelWithSpacer(patient.firstNameAttribute());
            Map<String, Node> tableMiddleName = tableLabelWithSpacer(patient.middleNameAttribute());
            Map<String, Node> tableLastName = tableLabelWithSpacer(patient.lastNameAttribute());
            Map<String, Node> tableAge = tableLabelWithSpacer(patient.age());
            Map<String, Node> tableEmail = styledTableLabelWithSpacer(patient.emailAttribute(), "email");
            Map<String, Node> tableScore = tableLabelWithSpacer(patient.getScore());
            Map<String, Node> tableAction = tableButtonWithSpacer(patient, event -> onShowPatientButtonClick(patient));

            tableBody.getChildren().addAll(
                tableFirstName.get("label"), tableFirstName.get("spacer"),
                tableMiddleName.get("label"), tableMiddleName.get("spacer"),
                tableLastName.get("label"), tableLastName.get("spacer"),
                tableAge.get("label"), tableAge.get("spacer"),
                tableEmail.get("label"), tableEmail.get("spacer"),
                tableScore.get("label"), tableScore.get("spacer"),
                tableAction.get("button-box"), tableAction.get("spacer")
            );

            profilePatients.getChildren().add(tableBody);
        }
    }
}
