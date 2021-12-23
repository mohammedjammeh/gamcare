package com.projects.gamcare.core;

import com.projects.gamcare.Main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneTool {
    public void switchTo(Stage stage, String sceneName) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(sceneName)));

        Scene scene = new Scene(root, 1000, 1000);
        stage.setTitle("Gamcare");
        stage.setScene(scene);
        stage.show();
    }
}
