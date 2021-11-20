package com.projects.gamcare.controllers.patient.show;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Meals {
    @FXML
    private VBox errorBox;

    @FXML
    private Label errorLabel;


    @FXML
    private Label mealNameFieldLabel;

    @FXML
    private Label mealSizeFieldLabel;

    @FXML
    private Label mealDateFieldLabel;

    @FXML
    private DatePicker mealDatePicker;


    @FXML
    protected void onAddMealButtonClick() {
        System.out.println("You have now added a meal.");
    }

    @FXML
    protected void onPreviousMealWeekButtonClick() {
        System.out.println("You are now looking at the previous meal week.");
    }

    @FXML
    protected void onNextMealWeekButtonClick() {
        System.out.println("You are now looking at the next meal week.");
    }
}
