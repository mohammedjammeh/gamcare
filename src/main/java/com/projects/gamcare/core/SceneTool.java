package com.projects.gamcare.core;

import com.projects.gamcare.Main;
import com.projects.gamcare.controllers.hospital.Index;
import com.projects.gamcare.fields.main.BaseFields;
import com.projects.gamcare.models.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class SceneTool {
    public static void switchTo(Window window, String resourceName, User user) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/" + resourceName + ".fxml"));
        Parent root = loader.load();

        BaseFields controller = loader.getController();
        controller.setUser(user);
        controller.setUpHeader();

        showStage(root, (Stage) window);
    }

    public static void switchToLogin(Window window) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/login.fxml"));
        Parent root = loader.load();

        showStage(root, (Stage) window);
    }

    private static void showStage(Parent root, Stage stage) {
        Scene scene = new Scene(root, 1000, 1000);

        stage.setTitle("Gamcare");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
