package com.projects.gamcare.controllers;

import com.projects.gamcare.core.Controller;
import com.projects.gamcare.core.SceneTool;
import com.projects.gamcare.models.main.Model;
import com.projects.gamcare.models.main.ProfileUser;
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

public class ShowParent extends Controller {
    private ProfileUser profileUser;

    @FXML
    protected VBox innerBodyBox, profileAttributes, profileOtherDetails;


    /**
     * Getters & Setters
     */
    public void setProfileUser(ProfileUser profileUser) {
        this.profileUser = profileUser;
    }

    public ProfileUser getProfileUser() {
        return profileUser;
    }


    /**
     * Extend Methods (Attributes)
     */
    protected HBox topAttributesRow() {
        HBox topRow = new HBox();
        ObservableList<Node> topRowChildren = topRow.getChildren();

        topRowChildren.addAll(attributeBoxWithSpacer("Title:", getProfileUser().getTitle().nameAttribute()));
        topRowChildren.addAll(attributeBoxWithSpacer("Gender:", getProfileUser().getGender().nameAttribute()));
        topRowChildren.add(attributeBox("Date of birth:", getProfileUser().dateOfBirthDisplay()));

        return topRow;
    }

    protected HBox nameAttributesRow(Model model) {
        HBox namesRow = new HBox();
        ObservableList<Node> namesRowChildren = namesRow.getChildren();

        namesRowChildren.addAll(attributeBoxWithSpacer("First name:", model.firstNameAttribute()));
        namesRowChildren.addAll(attributeBoxWithSpacer("Middle name:", model.middleNameAttribute()));
        namesRowChildren.add(attributeBox("Last name:", model.lastNameAttribute()));

        return namesRow;
    }

    protected HBox contactAttributesRow(Model model) {
        HBox contactRow = new HBox();
        ObservableList<Node> contactRowChildren = contactRow.getChildren();

        contactRowChildren.addAll(attributeBoxWithSpacer("Email address:", model.getAttribute("email_address")));
        contactRowChildren.addAll(attributeBoxWithSpacer("Phone number:", model.getAttribute("phone_number")));
        contactRowChildren.add(attributeBox("Relevant link:", model.getAttribute("relevant_link")));

        return contactRow;
    }

    protected HBox addressAttributesRow(Model model) {
        HBox addressRow = new HBox();
        ObservableList<Node> addressRowChildren = addressRow.getChildren();

        addressRowChildren.addAll(attributeBoxWithSpacer("Compound:", model.getAttribute("compound")));
        addressRowChildren.addAll(attributeBoxWithSpacer("City / Town:", model.getAttribute("town")));
        addressRowChildren.add(attributeBox("Region:", model.getRegion().nameAttribute()));

        return addressRow;
    }

    protected List<HBox> attributeBoxWithSpacer(String name, Object value) {
        HBox spacerBox = new HBox();
        HBox.setHgrow(spacerBox, Priority.ALWAYS);

        return List.of(attributeBox(name, value), spacerBox);
    }

    protected HBox attributeBox(String name, Object value) {
        HBox attributeBox = newHBoxWithStyleClass("attribute");
        Label attributeLabel = newLabelWithStyleClass(name, "attributeLabel");
        Label attributeValue = newLabelWithStyleClass(value, "attributeValue");

        attributeBox.getChildren().addAll(List.of(attributeLabel, attributeValue));

        return attributeBox;
    }

    /**
     * Extend Methods (Build Section)
     */
    protected void buildOtherDetailsSection(Model model) {
        Object otherDetailsText = model.getAttribute("other_details");
        Label otherDetailsLabel = newLabelWithStyleClass(otherDetailsText, "");
        otherDetailsLabel.setLineSpacing(14);
        otherDetailsLabel.setWrapText(true);

        HBox otherDetailsLabelBox = new HBox();
        HBox row = new HBox();

        otherDetailsLabelBox.getChildren().add(otherDetailsLabel);
        row.getChildren().add(otherDetailsLabelBox);
        profileOtherDetails.getChildren().add(row);
    }

    protected void buildActionsSubHeading() {
        Label title = new Label("Actions");

        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox box = newHBoxWithStyleClass("subHeadingBox");
        box.getChildren().addAll(title, spacer);

        innerBodyBox.getChildren().add(box);
    }

    protected void buildActionsSection(EventHandler<ActionEvent> actionEvent) {
        Button deleteAccountButton = new Button(authUserViewingOwnProfile() ? "Delete My Account" : "Delete Account");
        deleteAccountButton.setOnAction(actionEvent);

        HBox innerActionsBox = new HBox();
        innerActionsBox.getChildren().add(deleteAccountButton);

        VBox actionsBox = newVBoxWithStyleClass("profileAction");
        actionsBox.getChildren().add(innerActionsBox);

        innerBodyBox.getChildren().add(actionsBox);
    }

    /**
     * Extend Methods (General)
     */
    protected void switchSceneAfterUserDelete(String resourceName) {
        if (authUserViewingOwnProfile()) {
            SceneTool.switchToLogin();
            SceneTool.closeWindow(innerBodyBox);
            return;
        }

        SceneTool.switchTo(resourceName, getAuthUser());
        SceneTool.closeWindow(innerBodyBox);
    }
}
