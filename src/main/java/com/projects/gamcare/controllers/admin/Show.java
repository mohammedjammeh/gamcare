package com.projects.gamcare.controllers.admin;

import com.projects.gamcare.controllers.ShowParent;
import javafx.fxml.FXML;

public class Show extends ShowParent {
    @FXML
    protected void onEditAdminButtonClick() {
        System.out.println("You have now edit a hospital.");
    }

    public void setUpBody() {
        buildAttributesSection();
        buildOtherDetailsSection(getProfileUser());
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
