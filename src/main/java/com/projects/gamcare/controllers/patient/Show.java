package com.projects.gamcare.controllers.patient;

import com.projects.gamcare.controllers.ShowParent;
import javafx.fxml.FXML;

public class Show extends ShowParent {
    @FXML
    protected void onEditPatientButtonClick() {
        System.out.println("You can now edit patient profile.");
    }

    public void setUpBody() {
        buildAttributesSection();
//        buildOtherDetailsSection(getProfileUser());
    }

    private void buildAttributesSection() {
        profileAttributes.getChildren().addAll(
            topAttributesRow(),
            nameAttributesRow(getProfileUser()),
            contactAttributesRow(getProfileUser()),
            addressAttributesRow(getProfileUser())
        );
    }
}
