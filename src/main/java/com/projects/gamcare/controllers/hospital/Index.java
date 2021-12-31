package com.projects.gamcare.controllers.hospital;

import com.projects.gamcare.core.Controller;
import com.projects.gamcare.models.Doctor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Index extends Controller {
    @FXML
    protected VBox hospitalBodyBox;

    @FXML
    protected void onAddHospitalButtonClick() {
        System.out.println("You have now added a hospital.");
    }

    @FXML
    protected void onShowHospitalButtonClick() {
        System.out.println("You can now see a hospital.");
    }

    public void setUpBody() {
        HBox row = new HBox();

        Button button = new Button();
        button.setText("Real Friends");
        button.setOnAction(event -> onShowHospitalButtonClick());

        row.getChildren().add(button);

        hospitalBodyBox.getChildren().add(row);


        
        Doctor doctor = getUser().getDoctor();
//        System.out.println(doctor.getHospitals().get(0).attributes);
//        doctor.getHospitals().stream().map(item -> item.attributes.get("name")).forEach(System.out::println);

    }
}
