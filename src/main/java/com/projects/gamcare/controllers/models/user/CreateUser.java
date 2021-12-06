package com.projects.gamcare.controllers.models.user;

import com.projects.gamcare.controllers.models.CreateModel;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class CreateUser extends CreateModel {
    @FXML
    protected TextField firstNameTextField;

    @FXML
    protected TextField middleNameTextField;

    @FXML
    protected TextField lastNameTextField;


    @FXML
    protected ChoiceBox<String> titleChoiceBox;

    @FXML
    protected ChoiceBox<String> genderChoiceBox;

    @FXML
    protected ChoiceBox<String> tribeChoiceBox;


    @FXML
    protected TextField placeOfBirthTextField;

    @FXML
    protected DatePicker dateOfBirthPicker;
}
