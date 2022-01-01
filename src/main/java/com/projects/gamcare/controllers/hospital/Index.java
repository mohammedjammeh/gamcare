package com.projects.gamcare.controllers.hospital;

import com.projects.gamcare.core.Controller;
import com.projects.gamcare.models.Hospital;
import com.projects.gamcare.models.main.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

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
        List<Model> hospitals = getUser().getHospitals();
        HBox row = new HBox();

        for (int i = 0; i < hospitals.size(); i++) {
            Model hospital = hospitals.get(i);

            Button button = new Button();
            button.setText(hospital.nameAttribute());
            button.setOnAction(event -> onShowHospitalButtonClick());

            row = hasNoSpace(row) ?  new HBox() : row;
            row.getChildren().add(button);

            if (hasNoSpace(row) || isLastItem(hospitals, i)) {
                hospitalBodyBox.getChildren().add(row);
            }
        }
    }

    private Boolean hasNoSpace(HBox row) {
        return (long) row.getChildren().size() >= 4;
    }

    private Boolean isLastItem( List<Model> hospitals, Integer hospitalIndex) {
        return hospitals.size() - 1 == hospitalIndex;
    }
}
