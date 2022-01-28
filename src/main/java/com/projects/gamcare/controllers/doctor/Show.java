package com.projects.gamcare.controllers.doctor;

import com.projects.gamcare.controllers.ShowParent;
import com.projects.gamcare.core.SceneTool;
import com.projects.gamcare.models.Doctor;
import com.projects.gamcare.models.Hospital;
import com.projects.gamcare.models.main.Model;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Show extends ShowParent {
    private Doctor doctor;

    @FXML
    protected VBox profileHospitals;

    @FXML
    protected void onShowHospitalButtonClick(Hospital hospital) {
        SceneTool.switchToHospital("show", getAuthUser(), hospital);
        SceneTool.closeWindow(profileHospitals);
    }

    @FXML
    protected void onEditDoctorButtonClick() {
        System.out.println("You have now edit a hospital.");
    }

    @FXML
    protected void onDeleteDoctorButtonClick() {
        System.out.println("You have now edit a hospital.");
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
