package com.projects.gamcare.core;

import com.projects.gamcare.enums.UserType;
import com.projects.gamcare.models.Doctor;
import com.projects.gamcare.models.User;
import com.projects.gamcare.models.main.Model;
import com.projects.gamcare.models.main.ProfileUser;
import com.projects.gamcare.wrappers.ChoiceBox;
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
import java.util.List;

public class Controller {
    private User authUser;

    @FXML
    private VBox headingBox, errorBox;


    /**
     * Setters & Getters
     */
    public void setAuthUser(User authUser) {
        this.authUser = authUser;
    }

    public User getAuthUser() {
        return authUser;
    }

    public ProfileUser getProfileUser() { return null; }

    public void setUpBody() {}

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
        Button hospitalsButton = newButtonWithAction("Hospitals", switchSceneTo("hospital/index"));
        Button doctorsButton = newButtonWithAction("Doctors", switchSceneTo("doctor/index"));
        Button myProfileButton = newButtonWithAction("My Profile", switchSceneToProfile());
        Button logOutButton = newButtonWithAction("Log Out", event -> SceneTool.switchToLogin());

        return userHeaderButtons(hospitalsButton, doctorsButton, myProfileButton, logOutButton);
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

        if (authUserViewingOwnProfile()) {
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

    protected boolean authUserViewingOwnProfile() {
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


    /**
     * Fields Methods
     */
    public void hideErrorBox() {
        errorBox.setVisible(false);
        errorBox.setManaged(false);
    }

    public void showErrorBox() {
        errorBox.setVisible(true);
        errorBox.setManaged(true);
    }

    public void hide(Button button) {
        button.setVisible(false);
        button.setManaged(false);
    }

    public List<String> getEnumItems(Class<?> enumClass) {
        return Arrays
            .stream(enumClass.getEnumConstants())
            .map(Object::toString)
            .map(StringTool::capitalise)
            .toList();
    }

    public List<String> getNames(List<Model> models) {
        return models.stream().map(Model::nameAttribute).toList();
    }

    public List<String> getFullNames(List<Model> models) {
        return models.stream().map(Model::fullNameAttribute).toList();
    }


    /**
     * Extend Methods (Button Click)
     */
    @FXML
    public void onShowDoctorButtonClick(Doctor doctor) {
        SceneTool.switchToProfile("doctor/show", getAuthUser(), doctor);
    }


    /**
     * Extend Methods (Table Display)
     */
    protected void buildDoctorsSection(List<Model> doctors, VBox section) {
        for (Model doctorModel : doctors) {
            Doctor doctor = (Doctor) doctorModel;
            HBox tableBody = newHBoxWithStyleClass("tableBody");

            Map<String, Node> tableFirstName = tableLabelWithSpacer(doctor.firstNameAttribute());
            Map<String, Node> tableMiddleName = tableLabelWithSpacer(doctor.middleNameAttribute());
            Map<String, Node> tableLastName = tableLabelWithSpacer(doctor.lastNameAttribute());
            Map<String, Node> tableAge = tableLabelWithSpacer(doctor.age());
            Map<String, Node> tableEmail = styledTableLabelWithSpacer(doctor.emailAttribute(), "email");
            Map<String, Node> tableCareerLevel = tableLabelWithSpacer(doctor.careerLevelAttribute());
            Map<String, Node> tableAction = tableButtonWithSpacer(doctor, event -> onShowDoctorButtonClick(doctor));

            tableBody.getChildren().addAll(
                tableFirstName.get("label"), tableFirstName.get("spacer"),
                tableMiddleName.get("label"), tableMiddleName.get("spacer"),
                tableLastName.get("label"), tableLastName.get("spacer"),
                tableAge.get("label"), tableAge.get("spacer"),
                tableEmail.get("label"), tableEmail.get("spacer"),
                tableCareerLevel.get("label"), tableCareerLevel.get("spacer"),
                tableAction.get("button-box"), tableAction.get("spacer")
            );

            section.getChildren().add(tableBody);
        }
    }

    protected Map<String, Node> tableLabelWithSpacer(String attributeName) {
        Label tableLabel = new Label(attributeName);
        HBox tableLabelSpacer = new HBox();
        HBox.setHgrow(tableLabelSpacer, Priority.ALWAYS);

        return tableNodeWithSpacer("label", tableLabel, "spacer", tableLabelSpacer);
    }

    protected Map<String, Node> styledTableLabelWithSpacer(String attributeName, String styleClass) {
        Label tableLabel = newLabelWithStyleClass(attributeName, styleClass);
        HBox tableLabelSpacer = new HBox();
        HBox.setHgrow(tableLabelSpacer, Priority.ALWAYS);

        return tableNodeWithSpacer("label", tableLabel, "spacer", tableLabelSpacer);
    }

    protected Map<String, Node> tableButtonWithSpacer(Model patient, EventHandler<ActionEvent> event) {
        Button tableButton = new Button("Profile");
        tableButton.setOnAction(event);

        HBox tableButtonBox = newHBoxWithStyleClass("action");
        tableButtonBox.getChildren().add(tableButton);

        HBox tableButtonBoxSpacer = newHBoxWithAlwaysHGrow();

        return tableNodeWithSpacer("button-box", tableButtonBox, "spacer", tableButtonBoxSpacer);
    }

    protected Map<String, Node> tableNodeWithSpacer(String nodeKey, Node actualNode,  String spacerKey, Node actualSpacer) {
        Map<String, Node> nodeWithSpacer = new HashMap<>();
        nodeWithSpacer.put(nodeKey, actualNode);
        nodeWithSpacer.put(spacerKey, actualSpacer);

        return nodeWithSpacer;
    }


    /**
     * Extend Methods (General)
     */
    protected HBox newHBoxWithAlwaysHGrow() {
        HBox newHBox = new HBox();
        HBox.setHgrow(newHBox, Priority.ALWAYS);

        return newHBox;
    }

    protected HBox newHBoxWithStyleClass(String styleClass) {
        HBox newHBox = new HBox();
        newHBox.getStyleClass().add(styleClass);

        return newHBox;
    }

    protected VBox newVBoxWithStyleClass(String styleClass) {
        VBox newVBox = new VBox();
        newVBox.getStyleClass().add(styleClass);

        return newVBox;
    }

    protected Label newLabelWithStyleClass(Object labelTextObject, String styleClass) {
        String labelText = Optional.ofNullable(labelTextObject).isEmpty()
            ? "N/A"
            : String.valueOf(labelTextObject);

        Label newLabel = new Label(labelText);
        newLabel.getStyleClass().add(styleClass);

        return newLabel;
    }

    protected Button newButtonWithAction(String name, EventHandler<ActionEvent> action) {
        Button newButton = new Button(name);
        newButton.setOnAction(action);

        return newButton;
    }
}
