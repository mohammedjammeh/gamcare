package com.projects.gamcare.controllers.admin;

import com.projects.gamcare.controllers.ShowParent;
import com.projects.gamcare.core.TimeTool;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

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

    private HBox topAttributesRow() {
        HBox topRow = new HBox();
        ObservableList<Node> topRowChildren = topRow.getChildren();

        topRowChildren.addAll(attributeBoxWithSpacer("Title:", getProfileUser().getTitle().nameAttribute()));
        topRowChildren.addAll(attributeBoxWithSpacer("Gender:", getProfileUser().getGender().nameAttribute()));
        topRowChildren.add(attributeBox("Date of birth:", TimeTool.dateOfBirthDisplay(getProfileUser().dateOfBirthAttribute())));

        return topRow;
    }
}
