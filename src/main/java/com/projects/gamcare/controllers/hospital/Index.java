package com.projects.gamcare.controllers.hospital;

import com.projects.gamcare.core.Controller;
import com.projects.gamcare.core.SceneTool;
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
    protected Button addHospitalButton;

    @FXML
    protected void onAddHospitalButtonClick() {
        SceneTool.switchTo("hospital/create", getAuthUser());
    }

    @FXML
    protected void onShowHospitalButtonClick(Hospital hospital) {
        SceneTool.switchToHospitalShow(getAuthUser(), hospital);
    }

    public void setUpBody() {
        updateAddHospitalButtonVisibility();
        buildHospitalsSection();
    }

    private void updateAddHospitalButtonVisibility() {
        if (getAuthUser().isNotAdmin()) {
            hide(addHospitalButton);
        }
    }

    private void buildHospitalsSection() {
        List<Model> hospitals = getAuthUser().getHospitals();
        HBox row = new HBox();

        for (int i = 0; i < hospitals.size(); i++) {
            Hospital hospital = (Hospital) hospitals.get(i);

            Button button = new Button(hospital.nameAttribute());
            button.setOnAction(event -> onShowHospitalButtonClick(hospital));

            row = hasNoSpace(row) ? new HBox() : row;
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
