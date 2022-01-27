package com.projects.gamcare.controllers.hospital.parents;

import com.projects.gamcare.core.Controller;
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

public class Fields extends Controller {
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

    /**
     * Getters & Setters
     */
    public TreeMap<String, Object> getInputData() {
        TreeMap<String, Object> data = new TreeMap<>();

        data.put("name", nameTextField.getText());
        data.put("size", sizeChoiceBox.getValue());
        data.put("email_address", emailAddressInput());
        data.put("phone_number", phoneNumberTextField.getText());
        data.put("relevant_link", relevantLinkTextField.getText());
        data.put("compound", compoundNameTextField.getText());
        data.put("town", townTextField.getText());
        data.put("regions_id", regions.get(regionIndexInput()).idAttribute());
        data.put("other_details", otherDetailsTextArea.getText());
        data.put("updated_at", TimeTool.newDate());

        return data;
    }

    public String emailAddressInput() {
        return emailAddressTextField.getText();
    }

    public Integer regionIndexInput() {
        return regionChoiceBox.getSelectionModel().getSelectedIndex();
    }

    public Integer leadDoctorIndexInput() {
        return leadDoctorChoiceBox.getSelectionModel().getSelectedIndex();
    }

    public void setInputData(Hospital hospital) {
        nameTextField.setText(hospital.getAttribute("name").toString());
        sizeChoiceBox.setValue(hospital.getAttribute("size").toString());
        leadDoctorChoiceBox.getSelectionModel().select(leadDoctors.indexOf(hospital.getLeadDoctor()));
        emailAddressTextField.setText(hospital.getAttribute("email_address").toString());
        phoneNumberTextField.setText(hospital.getAttribute("phone_number").toString());
        relevantLinkTextField.setText(hospital.getAttribute("relevant_link").toString());
        compoundNameTextField.setText(hospital.getAttribute("compound").toString());
        townTextField.setText(hospital.getAttribute("town").toString());
        regionChoiceBox.getSelectionModel().select(regions.indexOf(hospital.getRegion()));
        otherDetailsTextArea.setText(hospital.getAttribute("other_details").toString());
    }

    protected Hospital getHospitalFromDB() {
        return (Hospital) (new Hospital()).where("email_address", emailAddressInput()).first();
    }

}
