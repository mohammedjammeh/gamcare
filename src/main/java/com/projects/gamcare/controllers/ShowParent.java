package com.projects.gamcare.controllers;

import com.projects.gamcare.core.Controller;
import com.projects.gamcare.core.TimeTool;
import com.projects.gamcare.models.User;
import com.projects.gamcare.models.main.Model;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.*;

public class ShowParent extends Controller {
    private User profileUser;

    @FXML
    protected VBox profileAttributes, profileOtherDetails;


    /**
     * Setters & Getters
     */
    public void setProfileUser(User profileUser) {
        this.profileUser = profileUser;
    }

    public User getProfileUser() {
        return profileUser;
    }


    /**
     * Extend Methods
     */
    protected HBox topAttributesRow() {
        HBox topRow = new HBox();
        ObservableList<Node> topRowChildren = topRow.getChildren();

        topRowChildren.addAll(attributeBoxWithSpacer("Title:", getProfileUser().getTitle().nameAttribute()));
        topRowChildren.addAll(attributeBoxWithSpacer("Gender:", getProfileUser().getGender().nameAttribute()));
        topRowChildren.add(attributeBox("Date of birth:", TimeTool.dateOfBirthDisplay(getProfileUser().dateOfBirthAttribute())));

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
        contactRowChildren.addAll(attributeBoxWithSpacer("Number:", model.getAttribute("phone_number")));
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
}
