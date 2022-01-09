package com.projects.gamcare.controllers.patient;

import com.projects.gamcare.controllers.ShowParent;
import com.projects.gamcare.models.Patient;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Show extends ShowParent {
    private Patient patient;

    @FXML
    protected VBox innerBodyBox;

    @FXML
    protected void onEditPatientButtonClick() {
        System.out.println("You can now edit patient profile.");
    }

    @FXML
    protected void onDeletePatientButtonClick() {
        System.out.println("You have now deleted patient profile.");
    }

    public void setUpBody() {
        patient = getProfileUser().getPatient();

        buildAttributesSection();
        buildOtherDetailsSection(getProfileUser());

        if(getAuthUser().isAdmin() || getAuthUser().isPatient()) {
            buildActionsSection();
        }
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

    private void buildActionsSection() {
        buildActionsSubHeading();
        buildActions();
    }

    private void buildActionsSubHeading() {
        Label title = new Label("Actions");

        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox box = newHBoxWithStyleClass("subHeadingBox");
        box.getChildren().addAll(title, spacer);

        innerBodyBox.getChildren().add(box);
    }

    private void buildActions() {
        Button deleteAccountButton = new Button(authUserViewingOwnProfile() ? "Delete My Account" : "Delete Account");
        deleteAccountButton.setOnAction(actionEvent -> onDeletePatientButtonClick());

        HBox innerActionsBox = new HBox();
        innerActionsBox.getChildren().add(deleteAccountButton);

        VBox actionsBox = newVBoxWithStyleClass("profileAction");
        actionsBox.getChildren().add(innerActionsBox);

        innerBodyBox.getChildren().add(actionsBox);
    }
}
