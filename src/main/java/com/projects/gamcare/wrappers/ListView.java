package com.projects.gamcare.wrappers;

import javafx.geometry.Orientation;
import javafx.scene.control.SelectionMode;
import java.util.List;

public class ListView extends javafx.scene.control.ListView<String> {
    public ListView() {
        super();
        this.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.setOrientation(Orientation.VERTICAL);
    }

    public void setItems(List<String> items) {
        this.getItems().addAll(items);
    }
}
