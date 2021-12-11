package com.projects.gamcare.wrappers;

import com.projects.gamcare.core.StringTool;

import java.util.Arrays;
import java.util.List;

public class ChoiceBox extends javafx.scene.control.ChoiceBox<String> {
    public void setItems(List<String> items) {
        this.getItems().addAll(items);
        this.setValue(items.get(0));
    }
}
