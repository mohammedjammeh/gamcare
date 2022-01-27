package com.projects.gamcare.core;

import com.projects.gamcare.Main;
import com.projects.gamcare.controllers.ShowParent;
import com.projects.gamcare.controllers.hospital.Edit;
import com.projects.gamcare.controllers.hospital.Show;
import com.projects.gamcare.controllers.user.CreateParent;
import com.projects.gamcare.models.Hospital;
import com.projects.gamcare.models.User;
import com.projects.gamcare.models.main.ProfileUser;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneTool {
    public static void switchTo(String resourceName, User authUser) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/" + resourceName + ".fxml"));
        Parent root = getLoadedRoot(loader);

        Controller controller = loader.getController();
        controller.setAuthUser(authUser);
        controller.setUpHeader();
        controller.setUpBody();

        showStage(root);
    }

    public static void switchToProfile(String resourceName, User authUser, ProfileUser profileUser) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/" + resourceName + ".fxml"));
        Parent root = getLoadedRoot(loader);

        ShowParent controller = loader.getController();
        controller.setAuthUser(authUser);
        controller.setProfileUser(profileUser);
        controller.setUpHeader();
        controller.setUpBody();

        showStage(root);
    }

    public static void switchToUserCreate(String resourceName, User authUser, Hospital hospital) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/" + resourceName + ".fxml"));
        Parent root = getLoadedRoot(loader);

        CreateParent controller = loader.getController();
        controller.setAuthUser(authUser);
        controller.setHospital(hospital);
        controller.setUpHeader();
        controller.setUpBody();
        controller.setUpAddForm();
        controller.setUpCreateForm();

        showStage(root);
    }

    public static void switchToHospitalShow(User authUser, Hospital hospital) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/hospital/show.fxml"));
        Parent root = getLoadedRoot(loader);

        Show controller = loader.getController();
        controller.setAuthUser(authUser);
        controller.setHospital(hospital);
        controller.setUpHeader();
        controller.setUpBody();

        showStage(root);
    }

    public static void switchToHospitalEdit(User authUser, Hospital hospital) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/hospital/edit.fxml"));
        Parent root = getLoadedRoot(loader);

        Edit controller = loader.getController();
        controller.setAuthUser(authUser);
        controller.setHospital(hospital);
        controller.setInputData(hospital);
        controller.setUpHeader();
        controller.setUpBody();

        showStage(root);
    }

    public static void switchToLogin() {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/login.fxml"));
        Parent root = getLoadedRoot(loader);

        showStage(root);
    }

    public static void closeWindow(Node node) {
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
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

    private static void showStage(Parent root) {
        Scene scene = new Scene(root, 1000, 1000);

        Stage stage = new Stage();
        stage.setTitle("Gamcare");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
