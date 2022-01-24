package com.projects.gamcare.controllers.hospital;

import com.projects.gamcare.core.Controller;
import com.projects.gamcare.core.SceneTool;
import com.projects.gamcare.core.TimeTool;
import com.projects.gamcare.enums.HospitalSize;
import com.projects.gamcare.models.Doctor;
import com.projects.gamcare.models.Hospital;
import com.projects.gamcare.models.Region;
import com.projects.gamcare.models.main.Model;
import com.projects.gamcare.wrappers.ChoiceBox;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.TreeMap;

public class Create extends Controller {
    protected List<Model> leadDoctors, regions;

    @FXML
    protected TextField nameTextField;

    @FXML
    protected ChoiceBox sizeChoiceBox, leadDoctorChoiceBox, regionChoiceBox;

    @FXML
    protected TextField emailAddressTextField, phoneNumberTextField, relevantLinkTextField;

    @FXML
    protected TextField compoundNameTextField, townTextField;

    @FXML
    protected TextArea otherDetailsTextArea;

    public void initialize() {
        hideErrorBox();

        leadDoctors = (new Doctor()).getAvailableSeniors();
        regions = (new Region()).getAll();

        leadDoctorChoiceBox.setItems(getFullNames(leadDoctors));
        regionChoiceBox.setItems(getNames(regions));
        sizeChoiceBox.setItems(getEnumItems(HospitalSize.class));
    }

    @FXML
    protected void onCreateHospitalButtonClick() {
        (new Hospital())
            .insert(newHospitalData());

        Hospital newHospital = getNewHospital();
        Doctor leadDoctor = (Doctor) leadDoctors.get(leadDoctorIndexInput());

        (new Model())
            .setTableName("hospitals_doctors")
            .insert(newHospitalDoctorData(newHospital, leadDoctor, 1));

        SceneTool.switchToHospitalShow(getAuthUser(), newHospital);
    }

    protected TreeMap<String, Object> newHospitalData() {
        TreeMap<String, Object> data = new TreeMap<>();

        data.put("name", nameInput());
        data.put("size", sizeInput());
        data.put("email_address", emailAddressInput());
        data.put("phone_number", phoneNumberInput());
        data.put("relevant_link", relevantLinkInput());
        data.put("compound", compoundNameInput());
        data.put("town", townInput());
        data.put("regions_id", regions.get(regionIndexInput()).idAttribute());
        data.put("created_at", TimeTool.newDate());
        data.put("updated_at", TimeTool.newDate());
        data.put("other_details", otherDetailsInput());

        return data;
    }

    protected Hospital getNewHospital() {
        return (Hospital) (new Hospital()).where("email_address", emailAddressInput()).first();
    }

    protected String nameInput() {
        return nameTextField.getText();
    }

    protected String sizeInput() {
        return sizeChoiceBox.getValue();
    }

    protected Integer leadDoctorIndexInput() {
        return leadDoctorChoiceBox.getSelectionModel().getSelectedIndex();
    }

    protected String emailAddressInput() {
        return emailAddressTextField.getText();
    }

    protected String phoneNumberInput() {
        return phoneNumberTextField.getText();
    }

    protected String relevantLinkInput() {
        return relevantLinkTextField.getText();
    }

    protected String compoundNameInput() {
        return compoundNameTextField.getText();
    }

    protected String townInput() {
        return townTextField.getText();
    }

    protected Integer regionIndexInput() {
        return regionChoiceBox.getSelectionModel().getSelectedIndex();
    }

    protected String otherDetailsInput() {
        return otherDetailsTextArea.getText();
    }
}
