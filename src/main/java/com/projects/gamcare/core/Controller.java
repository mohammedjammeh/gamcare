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
        Button hospitalsMenuButton = newButtonWithResource("Hospitals", "hospital/index");
        Button doctorsMenuButton = newButtonWithResource("Doctors", "doctor/index");
        Button myProfileMenuButton = newButtonWithResource("My Profile", "patient/show");
        Button logOutButton = newButtonWithAction("Log Out", event -> SceneTool.switchToLogin());

        List<Button> buttons = userHeaderButtons(hospitalsMenuButton, doctorsMenuButton, myProfileMenuButton, logOutButton);

        if (thisIsInstanceOfHospitalController()) {
            hospitalsMenuButton.getStyleClass().add("active");
            return buttons;
        }

        if (thisIsInstanceOfDoctorController()) {
            if(thisIsInstanceOfDoctorShowController() && authUserProfileIsBeingViewed()) {
                myProfileMenuButton.getStyleClass().add("active");
                return buttons;
            }

            doctorsMenuButton.getStyleClass().add("active");
            return buttons;
        }

        if (thisIsInstanceOfPatientShowController() && authUserProfileIsBeingViewed()) {
            myProfileMenuButton.getStyleClass().add("active");
            return buttons;
        }

        return buttons;
    }

    private Button newButtonWithResource(String name, String resourceName) {
        Button newButton = new Button(name);
        newButton.setOnAction(event -> SceneTool.switchTo(resourceName, getAuthUser()));

        return newButton;
    }

    private Button newButtonWithAction(String name, EventHandler<ActionEvent> action) {
        Button newButton = new Button(name);
        newButton.setOnAction(action);

        return newButton;
    }

    private List<Button> userHeaderButtons(Button hospitalsButton, Button doctorsMenuButton, Button myProfileMenuButton, Button logOutButton) {
        Map<String, List<Button>> resourceNames = new HashMap<>();

        resourceNames.put(UserType.PATIENT.name(), List.of(myProfileMenuButton, logOutButton));
        resourceNames.put(UserType.DOCTOR.name(), List.of(hospitalsButton, myProfileMenuButton, logOutButton));
        resourceNames.put(UserType.ADMIN.name(), List.of(hospitalsButton, doctorsMenuButton, myProfileMenuButton, logOutButton));

        return resourceNames.get(getAuthUser().typeAttribute());
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

    private boolean thisIsInstanceOfDoctorShowController() {
        return getClass().isInstance(new com.projects.gamcare.controllers.doctor.Show());
    }

    private boolean thisIsInstanceOfPatientShowController() {
        return getClass().isInstance(new com.projects.gamcare.controllers.patient.Show());
    }

    private boolean authUserProfileIsBeingViewed() {
        if (Optional.ofNullable(getProfileUser()).isEmpty()) {
            return false;
        }

        return getAuthUser().idAttribute() == getProfileUser().idAttribute();
    }
}
