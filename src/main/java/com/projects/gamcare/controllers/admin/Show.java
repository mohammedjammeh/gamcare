package com.projects.gamcare.controllers.admin;

import com.projects.gamcare.controllers.ShowParent;
import com.projects.gamcare.core.SceneTool;
import javafx.fxml.FXML;

public class Show extends ShowParent {
    @FXML
    protected void onEditAdminButtonClick() {
        SceneTool.switchToUserEdit("admin", getAuthUser(), getProfileUser());
        SceneTool.closeWindow(profileAttributes);
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
