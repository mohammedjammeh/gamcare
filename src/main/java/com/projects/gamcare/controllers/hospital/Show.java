package com.projects.gamcare.controllers.hospital;

import com.projects.gamcare.core.Controller;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class Show extends Controller {
    @FXML
    protected VBox profileAttributes;

    @FXML
    protected VBox profileOtherDetails;

    @FXML
    protected void onEditHospitalButtonClick() {
        System.out.println("You have now edit a hospital.");
    }

    @FXML
    protected void onAddPatientButtonClick() {
        System.out.println("You have now added a patient.");
    }

    @FXML
    protected void onShowPatientButtonClick() {
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
        buildProfileAttributes();
        buildOtherDetailsSection();
    }

    // Add or at least check <HBox HBox.hgrow="ALWAYS"/>
    private void buildProfileAttributes() {
        ObservableList<Node> row01Children = new HBox().getChildren();
        ObservableList<Node> row02Children = new HBox().getChildren();
        ObservableList<Node> row03Children = new HBox().getChildren();

        row01Children.add(attributeBox("Name:", getHospitalAttribute("name")));
        row01Children.add(attributeBox("Size:", getHospitalAttribute("size")));
        row01Children.add(attributeBox("Lead doctor:", getHospital().getLeadDoctor().fullNameAttribute()));

        row02Children.add(attributeBox("Email address:", getHospitalAttribute("email_address")));
        row02Children.add(attributeBox("Number:", getHospitalAttribute("phone_number")));
        row02Children.add(attributeBox("Relevant link:", getHospitalAttribute("relevant_link")));

        row03Children.add(attributeBox("Compound:", getHospitalAttribute("compound")));
        row03Children.add(attributeBox("City / Town:", getHospitalAttribute("town")));
        row03Children.add(attributeBox("Region:", getHospital().getRegion().nameAttribute()));

        profileAttributes.getChildren().addAll(
            row01Children.get(0).getParent(),
            row02Children.get(0).getParent(),
            row03Children.get(0).getParent()
        );
    }

    private HBox attributeBox(String name, String value) {
        Label attributeLabel = new Label(name);
        attributeLabel.getStyleClass().add("attributeLabel");

        Label attributeValue = new Label(value);
        attributeLabel.getStyleClass().add("attributeValue");

        HBox attributeBox = new HBox();
        attributeBox.getStyleClass().add("attribute");
        attributeBox.getChildren().addAll(List.of(attributeLabel, attributeValue));

        return attributeBox;
    }

    private String getHospitalAttribute(String name) {
        return (String) getHospital().attributes.get(name);
    }

    private void buildOtherDetailsSection() {
        Label otherDetails = new Label();
        otherDetails.setLineSpacing(14);
        otherDetails.setWrapText(true);
        otherDetails.setText(getHospitalAttribute("other_details"));

        HBox otherDetailsBox = new HBox();
        HBox row = new HBox();

        otherDetailsBox.getChildren().add(otherDetails);
        row.getChildren().add(otherDetailsBox);
        profileOtherDetails.getChildren().add(row);
    }
}
