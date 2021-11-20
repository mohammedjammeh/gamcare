package com.projects.gamcare.controllers.patient.show;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Exercises {
    @FXML
    private VBox errorBox;

    @FXML
    private Label errorLabel;




    @FXML
    private Label exerciseTypeFieldLabel;

    @FXML
    private Label exerciseSpanFieldLabel;

    @FXML
    private Label exerciseDateFieldLabel;

    @FXML
    private DatePicker exerciseDatePicker;




    @FXML
    protected void onAddExerciseButtonClick() {
        System.out.println("You have now added an exercise session.");
    }

    @FXML
    protected void onPreviousExerciseWeekButtonClick() {
        System.out.println("You are now looking at the previous exercise week.");
    }

    @FXML
    protected void onNextExerciseWeekButtonClick() {
        System.out.println("You are now looking at the next exercise week.");
    }
}
