package com.projects.gamcare.core;

import com.projects.gamcare.Main;
import com.projects.gamcare.controllers.Header;
import com.projects.gamcare.models.Hospital;
import com.projects.gamcare.models.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Controller {
    private User user;

    private Hospital hospital;

    @FXML
    private VBox errorBox;

    @FXML
    private VBox outerBodyBox;

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

    public void setUpHeader() {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/header.fxml"));
        Parent header = null;

        try {
            header = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Header headerController = loader.getController();
        headerController.setUser(user);
        headerController.setButtonsVisible();

        addToOuterBodyTop(header);
    }

    private void addToOuterBodyTop(Parent header) {
        ObservableList<Node> outerBodyChildren = outerBodyBox.getChildren();

        outerBodyChildren.add(header);

        int headerIndex = outerBodyChildren.indexOf(header);
        outerBodyChildren.get(headerIndex).toBack();
    }

    public void setUpBody() {}
}
