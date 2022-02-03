package com.projects.gamcare.controllers.doctor;

import com.projects.gamcare.controllers.ShowParent;
import com.projects.gamcare.core.SceneTool;
import com.projects.gamcare.models.Doctor;
import com.projects.gamcare.models.Hospital;
import com.projects.gamcare.models.User;
import com.projects.gamcare.models.main.Model;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Show extends ShowParent {
    private Doctor doctor;

    @FXML
    protected VBox profileHospitals;

    @FXML
    protected void onShowHospitalButtonClick(Hospital hospital) {
        SceneTool.switchToHospital("show", getAuthUser(), hospital);
        SceneTool.closeWindow(innerBodyBox);
    }

    @FXML
    protected void onEditDoctorButtonClick() {
        SceneTool.switchToUserEdit("doctor", getAuthUser(), getProfileUser());
        SceneTool.closeWindow(innerBodyBox);
    }

    @FXML
    protected void onDeleteDoctorButtonClick() {
        List<Map<String, Object>> doctorHospitalsRowsValues = doctor.getHospitals()
            .stream()
            .map(hospital -> doctorHospitalRowValues(doctor, (Hospital) hospital))
            .toList();

        (new Model())
            .setTableName("hospitals_doctors")
            .deleteManyWhere(doctorHospitalsRowsValues);

        getProfileUser().getDoctorUser().delete();

        doctor.delete();

        switchSceneAfterUserDelete("doctor/index");
    }

    public void setUpBody() {
        doctor = getProfileUser().getDoctor();

        buildAttributesSection();
        buildOtherDetailsSection(getProfileUser());
        buildHospitalsSection();

        if (getAuthUser().isAdmin()) {
            buildActionsSubHeading();
            buildActionsSection(actionEvent -> onDeleteDoctorButtonClick());
        }
    }

    private void buildAttributesSection() {
        profileAttributes.getChildren().addAll(
            topAttributesRow(),
            nameAttributesRow(getProfileUser()),
            doctorInfoAttributesRow(),
            contactAttributesRow(getProfileUser()),
            addressAttributesRow(getProfileUser())
        );
    }

    private HBox doctorInfoAttributesRow() {
        HBox doctorInfoRow = new HBox();
        ObservableList<Node> doctorInfoRowChildren = doctorInfoRow.getChildren();

        doctorInfoRowChildren.addAll(attributeBoxWithSpacer("University:", doctor.getAttribute("school")));
        doctorInfoRowChildren.addAll(attributeBoxWithSpacer("Field of study:", doctor.getAttribute("field_of_study")));
        doctorInfoRowChildren.add(attributeBox("Cereer Level:", doctor.careerLevelAttribute()));

        return doctorInfoRow;
    }

    private void buildHospitalsSection() {
        HBox hospitalButtonsBox = new HBox();

        for (Model hospitalModel: doctor.getHospitals()) {
            Hospital hospital = (Hospital) hospitalModel;

            Button button = new Button(hospital.nameAttribute());
            button.setOnAction(event -> onShowHospitalButtonClick(hospital));

            hospitalButtonsBox.getChildren().add(button);
        }

        profileHospitals.getChildren().add(hospitalButtonsBox);
    }
}
