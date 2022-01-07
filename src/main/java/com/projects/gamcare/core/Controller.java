package com.projects.gamcare.core;

import com.projects.gamcare.enums.UserType;
import com.projects.gamcare.models.Hospital;
import com.projects.gamcare.models.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.*;

public class Controller {
    private User authUser;

    private User profileUser;

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

    public void setAuthUser(User authUser) {
        this.authUser = authUser;
    }

    public User getAuthUser() {
        return authUser;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setProfileUser(User profileUser) {
        this.profileUser = profileUser;
    }

    public User getProfileUser() {
        return profileUser;
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

    public void setUpBody() {}

    private List<Button> headerButtons() {
        Button hospitalsButton = newButtonWithAction("Hospitals", switchSceneTo("hospital/index"));
        Button doctorsButton = newButtonWithAction("Doctors", switchSceneTo("doctor/index"));
        Button myProfileButton = newButtonWithAction("My Profile", switchSceneToProfile());
        Button logOutButton = newButtonWithAction("Log Out", event -> SceneTool.switchToLogin());

        return userHeaderButtons(hospitalsButton, doctorsButton, myProfileButton, logOutButton);
    }

    private Button newButtonWithAction(String name, EventHandler<ActionEvent> action) {
        Button newButton = new Button(name);
        newButton.setOnAction(action);

        return newButton;
    }

    private EventHandler<ActionEvent> switchSceneTo(String resourceName) {
        return event -> SceneTool.switchTo(resourceName, getAuthUser());
    }

    private EventHandler<ActionEvent> switchSceneToProfile() {
        return event -> SceneTool.switchToProfile(getAuthUser().profileResourceName(), getAuthUser(), getAuthUser());
    }

    private List<Button> userHeaderButtons(Button hospitalsButton, Button doctorsButton, Button myProfileButton, Button logOutButton) {
        Map<String, List<Button>> buttons = new HashMap<>();

        buttons.put(UserType.PATIENT.name(), List.of(logOutButton));
        buttons.put(UserType.DOCTOR.name(), List.of(hospitalsButton, myProfileButton, logOutButton));
        buttons.put(UserType.ADMIN.name(), List.of(hospitalsButton, doctorsButton, myProfileButton, logOutButton));

        List<Button> userButtons = buttons.get(getAuthUser().typeAttribute());

        if (authUserProfileIsBeingViewed()) {
            myProfileButton.getStyleClass().add("active");
            return userButtons;
        }

        if (thisIsInstanceOfHospitalController()) {
            hospitalsButton.getStyleClass().add("active");
            return userButtons;
        }

        if (thisIsInstanceOfDoctorController()) {
            doctorsButton.getStyleClass().add("active");
            return userButtons;
        }

        return userButtons;
    }

    private boolean authUserProfileIsBeingViewed() {
        if (Optional.ofNullable(getProfileUser()).isEmpty()) {
            return false;
        }

        return thisIsInstanceOfShowController() &&
            getAuthUser().idAttribute() == getProfileUser().idAttribute();
    }

    private boolean thisIsInstanceOfShowController() {
        return getClass().isInstance(new com.projects.gamcare.controllers.admin.Show()) ||
            getClass().isInstance(new com.projects.gamcare.controllers.doctor.Show()) ||
            getClass().isInstance(new com.projects.gamcare.controllers.patient.Show());
    }

    private boolean thisIsInstanceOfHospitalController() {
        return getClass().isInstance(new com.projects.gamcare.controllers.hospital.Create()) ||
            getClass().isInstance(new com.projects.gamcare.controllers.hospital.Index()) ||
            getClass().isInstance(new com.projects.gamcare.controllers.hospital.Show());
    }

    private boolean thisIsInstanceOfDoctorController() {
        return getClass().isInstance(new com.projects.gamcare.controllers.doctor.Create()) ||
            getClass().isInstance(new com.projects.gamcare.controllers.doctor.Index()) ||
            getClass().isInstance(new com.projects.gamcare.controllers.doctor.Show());
    }
}
