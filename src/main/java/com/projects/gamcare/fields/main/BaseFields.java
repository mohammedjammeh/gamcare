package com.projects.gamcare.fields.main;

import com.projects.gamcare.core.StringTool;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.List;

public class BaseFields {
    @FXML
    protected VBox errorBox;

    public void initialize() {
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
}
