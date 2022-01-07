package com.projects.gamcare.controllers.admin;

import com.projects.gamcare.core.Controller;
import com.projects.gamcare.models.Hospital;
import com.projects.gamcare.models.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Show extends Controller {
    @FXML
    protected VBox profileAttributes, profileOtherDetails;

    @FXML
    protected void onEditAdminButtonClick() {
        System.out.println("You have now edit a hospital.");
    }

    public void setUpBody() {
        buildAttributesSection();
    }

    private void buildAttributesSection() {
        User profileUser = getProfileUser();

        ObservableList<Node> row01Children = new HBox().getChildren();
        ObservableList<Node> row02Children = new HBox().getChildren();
        ObservableList<Node> row03Children = new HBox().getChildren();
        ObservableList<Node> row04Children = new HBox().getChildren();
        ObservableList<Node> row05Children = new HBox().getChildren();

//        row01Children.addAll(attributeBoxWithSpacer("Name:", profileUser.getAttribute("name")));
//        row01Children.addAll(attributeBoxWithSpacer("Size:", hospital.getAttribute("size")));
//        row01Children.add(attributeBox("Lead doctor:", hospital.getLeadDoctor().fullNameAttribute()));
    }
}
