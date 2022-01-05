package com.projects.gamcare.controllers.hospital;

import com.projects.gamcare.core.Controller;
import com.projects.gamcare.models.Hospital;
import com.projects.gamcare.models.Patient;
import com.projects.gamcare.models.main.Model;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.*;

public class Show extends Controller {
    @FXML
    protected VBox profileAttributes;

    @FXML
    protected VBox profileOtherDetails;

    @FXML
    protected VBox profilePatients;

    @FXML
    protected void onEditHospitalButtonClick() {
        System.out.println("You have now edit a hospital.");
    }

    @FXML
    protected void onAddPatientButtonClick() {
        System.out.println("You have now added a patient.");
    }

    @FXML
    protected void onShowPatientButtonClick(Patient patient) {
        System.out.println("You can now see a patient.");
    }

    @FXML
    protected void onAddDoctorButtonClick() {
        System.out.println("You have now added a doctor.");
    }

    @FXML
    protected void onShowDoctorButtonClick() {
        System.out.println("You can now see a doctor.");
    }

    public void setUpBody() {
        buildAttributesSection();
        buildOtherDetailsSection();
        buildPatientsSection();
    }

    private void buildAttributesSection() {
        Hospital hospital = getHospital();

        ObservableList<Node> row01Children = new HBox().getChildren();
        ObservableList<Node> row02Children = new HBox().getChildren();
        ObservableList<Node> row03Children = new HBox().getChildren();

        row01Children.addAll(attributeBoxWithSpacer("Name:", hospital.getAttribute("name")));
        row01Children.addAll(attributeBoxWithSpacer("Size:", hospital.getAttribute("size")));
        row01Children.add(attributeBox("Lead doctor:", hospital.getLeadDoctor().fullNameAttribute()));

        row02Children.addAll(attributeBoxWithSpacer("Email address:", hospital.getAttribute("email_address")));
        row02Children.addAll(attributeBoxWithSpacer("Number:", hospital.getAttribute("phone_number")));
        row02Children.add(attributeBox("Relevant link:", hospital.getAttribute("relevant_link")));

        row03Children.addAll(attributeBoxWithSpacer("Compound:", hospital.getAttribute("compound")));
        row03Children.addAll(attributeBoxWithSpacer("City / Town:", hospital.getAttribute("town")));
        row03Children.add(attributeBox("Region:", hospital.getRegion().nameAttribute()));

        profileAttributes.getChildren().addAll(
            row01Children.get(0).getParent(),
            row02Children.get(0).getParent(),
            row03Children.get(0).getParent()
        );
    }

    private List<HBox> attributeBoxWithSpacer(String name, String value) {
        HBox spacerBox = new HBox();
        HBox.setHgrow(spacerBox, Priority.ALWAYS);

        return List.of(attributeBox(name, value), spacerBox);
    }

    private HBox attributeBox(String name, String value) {
        HBox attributeBox = newHBoxWithStyleClass("attribute");
        Label attributeLabel = newLabelWithStyleClass(name, "attributeLabel");
        Label attributeValue = newLabelWithStyleClass(value, "attributeValue");

        attributeBox.getChildren().addAll(List.of(attributeLabel, attributeValue));

        return attributeBox;
    }

    private void buildOtherDetailsSection() {
        Label otherDetails = new Label();
        otherDetails.setLineSpacing(14);
        otherDetails.setWrapText(true);
        otherDetails.setText(getHospital().getAttribute("other_details"));

        HBox otherDetailsBox = new HBox();
        HBox row = new HBox();

        otherDetailsBox.getChildren().add(otherDetails);
        row.getChildren().add(otherDetailsBox);
        profileOtherDetails.getChildren().add(row);
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
            Map<String, Node> tableAction = tableButtonWithSpacer(patient);

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

    private Map<String, Node> tableLabelWithSpacer(String attributeName) {
        Label tableLabel = new Label(attributeName);
        HBox tableLabelSpacer = new HBox();
        HBox.setHgrow(tableLabelSpacer, Priority.ALWAYS);

        return tableNodeWithSpacer("label", tableLabel, "spacer", tableLabelSpacer);
    }

    private Map<String, Node> styledTableLabelWithSpacer(String attributeName, String styleClass) {
        Label tableLabel = newLabelWithStyleClass(attributeName, styleClass);
        HBox tableLabelSpacer = new HBox();
        HBox.setHgrow(tableLabelSpacer, Priority.ALWAYS);

        return tableNodeWithSpacer("label", tableLabel, "spacer", tableLabelSpacer);
    }

    private Map<String, Node> tableButtonWithSpacer(Patient patient) {
        Button tableButton = new Button("Profile");
        tableButton.setOnAction(event -> onShowPatientButtonClick(patient));

        HBox tableButtonBox = new HBox();
        tableButtonBox.getStyleClass().add("action");
        tableButtonBox.getChildren().add(tableButton);

        HBox tableButtonBoxSpacer = new HBox();
        HBox.setHgrow(tableButtonBoxSpacer, Priority.ALWAYS);

        return tableNodeWithSpacer("button-box", tableButtonBox, "spacer", tableButtonBoxSpacer);
    }

    private Map<String, Node> tableNodeWithSpacer(String nodeKey, Node actualNode,  String spacerKey, Node actualSpacer) {
        Map<String, Node> nodeWithSpacer = new HashMap<>();
        nodeWithSpacer.put(nodeKey, actualNode);
        nodeWithSpacer.put(spacerKey, actualSpacer);

        return nodeWithSpacer;
    }

    private HBox newHBoxWithStyleClass(String styleClass) {
        HBox newHBox = new HBox();
        newHBox.getStyleClass().add(styleClass);

        return newHBox;
    }

    private Label newLabelWithStyleClass(String labelText, String styleClass) {
        Label newLabel = new Label(labelText);
        newLabel.getStyleClass().add(styleClass);

        return newLabel;
    }
}
