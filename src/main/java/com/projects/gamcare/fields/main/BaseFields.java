package com.projects.gamcare.fields.main;

import com.projects.gamcare.Main;
import com.projects.gamcare.controllers.Header;
import com.projects.gamcare.core.StringTool;
import com.projects.gamcare.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.List;

public class BaseFields {
    private User user;

    @FXML
    private VBox outerBodyBox;

    @FXML
    protected VBox errorBox;

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

    public void setUpHeader() throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/header.fxml"));
        Parent header = loader.load();

        Header headerController = loader.getController();
        headerController.setUser(user);
        headerController.displayButtons();

        addToOuterBodyTop(header);
    }

    public void addToOuterBodyTop(Parent header) {
        outerBodyBox.getChildren().add(header);
        int headerIndex = outerBodyBox.getChildren().indexOf(header);
        outerBodyBox.getChildren().get(headerIndex).toBack();
    }
}
