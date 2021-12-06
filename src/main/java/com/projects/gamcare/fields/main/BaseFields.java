package com.projects.gamcare.fields.main;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class BaseFields {
    @FXML
    protected VBox errorBox;

    public void initialize() {
        errorBox.setVisible(false);
        errorBox.setManaged(false);
    }
}
