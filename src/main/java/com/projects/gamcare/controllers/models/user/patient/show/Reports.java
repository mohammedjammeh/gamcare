package com.projects.gamcare.controllers.models.user.patient.show;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class Reports {
    @FXML
    private VBox errorBox;

    @FXML
    private Label errorLabel;




    @FXML
    private Label reportDescriptionFieldLabel;

    @FXML
    private TextArea reportDescriptionTextArea;




    @FXML
    protected void onAddReportButtonClick() {
        System.out.println("You have now added a meal.");
    }

    @FXML
    protected void onPreviousWeekReportsButtonClick() {
        System.out.println("You are now looking at the previous week reports.");
    }

    @FXML
    protected void onNextWeekReportsButtonClick() {
        System.out.println("You are now looking at the next week reports.");
    }
}
