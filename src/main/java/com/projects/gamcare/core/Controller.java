package com.projects.gamcare.core;

import com.projects.gamcare.models.Hospital;
import com.projects.gamcare.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.List;

public class Controller {
    private User user;

    private Hospital hospital;

    @FXML
    private VBox headingBox;

    @FXML
    private VBox errorBox;

    public void hideErrorBox() {
        errorBox.setVisible(false);
        errorBox.setManaged(false);
    }

    public List<String> getEnumItems(Class<?> enumClass) {
        return Arrays
            .stream(enumClass.getEnumConstants())
            .map(Object::toString)
            .map(StringTool::capitalise)
            .toList();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setUpHeader() {
        Label titleLabel = new Label("Gamcare - Farokono");

        HBox mainTitleBox = new HBox();
        HBox.setHgrow(mainTitleBox, Priority.ALWAYS);
        mainTitleBox.getStyleClass().add("mainTitleBox");
        mainTitleBox.getChildren().add(titleLabel);

        HBox menuBox = new HBox();
        menuBox.getStyleClass().add("menuBox");
        menuBox.getChildren().addAll(headerButtons());

        HBox innerHeadingBox = new HBox();
        innerHeadingBox.getStyleClass().add("innerHeadingBox");
        innerHeadingBox.getChildren().add(mainTitleBox);
        innerHeadingBox.getChildren().add(menuBox);

        headingBox.getChildren().add(innerHeadingBox);
    }

    private List<Button> headerButtons() {
        Button hospitalsButton = new Button("Hospitals");
        setAction(hospitalsButton, "hospital/index");

        Button doctorsMenuButton = new Button("Doctors");
        setAction(doctorsMenuButton, "doctor/index");

        Button myProfileMenuButton = new Button("My Profile");
        setAction(myProfileMenuButton, "patient/show");

        Button logOutButton = new Button("Log Out");
        setAction(logOutButton);

        return List.of(hospitalsButton, doctorsMenuButton, myProfileMenuButton, logOutButton);
    }

    private void setAction(Button button, String nextResourceName) {
        button.setOnAction(event -> SceneTool.switchTo(nextResourceName, user));
    }

    private void setAction(Button button) {
        button.setOnAction(event -> SceneTool.switchToLogin());
    }

    public void setUpBody() {}
}
