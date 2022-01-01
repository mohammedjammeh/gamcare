package com.projects.gamcare.core;

import com.projects.gamcare.Main;
import com.projects.gamcare.models.Hospital;
import com.projects.gamcare.models.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class SceneTool {
    public static void switchTo(Window window, String resourceName, User user) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/" + resourceName + ".fxml"));
        Parent root = getLoadedRoot(loader);

        Controller controller = loader.getController();
        controller.setUser(user);
        controller.setUpHeader();
        controller.setUpBody();

        showStage(root, (Stage) window);
    }

    public static void switchTo(Window window, String resourceName, User user, Hospital hospital) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/" + resourceName + ".fxml"));
        Parent root = getLoadedRoot(loader);

        Controller controller = loader.getController();
        controller.setUser(user);
        controller.setHospital(hospital);
        controller.setUpHeader();
        controller.setUpBody();

        showStage(root, (Stage) window);
    }

    public static void switchToLogin(Window window) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/login.fxml"));
        Parent root = getLoadedRoot(loader);

        showStage(root, (Stage) window);
    }

    private static Parent getLoadedRoot(FXMLLoader loader) {
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return root;
    }

    private static void showStage(Parent root, Stage stage) {
        Scene scene = new Scene(root, 1000, 1000);

        stage.setTitle("Gamcare");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
