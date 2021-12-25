package com.projects.gamcare.core;

import com.projects.gamcare.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Objects;

public class SceneTool {
    public static void switchTo(String resourceName, Window window) throws IOException {
        String fullResourceName = "fxml/" + resourceName + ".fxml";
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fullResourceName)));

        Scene scene = new Scene(root, 1000, 1000);
        Stage stage = (Stage) window;

        stage.setTitle("Gamcare");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
