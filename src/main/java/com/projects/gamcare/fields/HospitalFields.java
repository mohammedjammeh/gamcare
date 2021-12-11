package com.projects.gamcare.fields;

import com.projects.gamcare.core.DB;
import com.projects.gamcare.enums.DoctorLevel;
import com.projects.gamcare.enums.HospitalSize;
import com.projects.gamcare.fields.main.BaseFields;
import com.projects.gamcare.wrappers.ChoiceBox;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;

public class HospitalFields extends BaseFields {
    @FXML
    protected TextField nameTextField;

    @FXML
    protected ChoiceBox sizeChoiceBox;

    @FXML
    protected ChoiceBox leadDoctorChoiceBox;

    @FXML
    protected TextField emailAddressTextField;

    @FXML
    protected TextField phoneNumberTextField;

    @FXML
    protected TextField relevantLinkTextField;

    @FXML
    protected TextField compoundNameTextField;

    @FXML
    protected TextField townTextField;

    @FXML
    protected ChoiceBox regionChoiceBox;

    @FXML
    protected TextArea otherDetailsTextArea;

    public String getName() {
        return nameTextField.getText();
    }

    public String getSize() {
        return sizeChoiceBox.getValue();
    }

    public Integer getLeadDoctorIndex() {
        return leadDoctorChoiceBox.getSelectionModel().getSelectedIndex();
    }

    public String getEmailAddress() {
        return emailAddressTextField.getText();
    }

    public String getPhoneNumber() {
        return phoneNumberTextField.getText();
    }

    public String getRelevantLink() {
        return relevantLinkTextField.getText();
    }

    public String getCompoundName() {
        return compoundNameTextField.getText();
    }

    public String getTown() {
        return townTextField.getText();
    }

    public Integer getRegionIndex() {
        return regionChoiceBox.getSelectionModel().getSelectedIndex();
    }

    public String getOtherDetails() {
        return otherDetailsTextArea.getText();
    }

    public void initialize() {
        super.initialize();

        regionChoiceBox.setItems(getDatabaseItems("regions"));
        sizeChoiceBox.setItems(getEnumItems(HospitalSize.class));

//        "SELECT COUNT(*) FROM doctors INNER JOIN users ON doctors.id = users.doctor_id WHERE doctors.career_level = ? "
        List<String> leadDoctors = new DB()
            .select("*")
            .from("doctors")
            .innerJoin("users")
            .on("doctors.id", "=", "users.id")
            .where("doctors.career_level", "=", DoctorLevel.SENIOR.toString())
            .orderBy("id")
            .get()
            .stream()
            .map(item -> item.get("name"))
            .toList();

//        leadDoctorChoiceBox.setItems(getEnumItems(HospitalSize.class));

    }
}
