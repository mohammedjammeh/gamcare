package com.projects.gamcare.controllers.hospital;

import com.projects.gamcare.models.Hospital;
import javafx.fxml.FXML;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Create extends CreateFields {
    public void initialize() {
        super.initialize();
        nameTextField.setText("Badibu");
        emailAddressTextField.setText("mohammedjamme@yahoo.com");
        phoneNumberTextField.setText("07506259330");
        relevantLinkTextField.setText("https://www.google.co.uk/");
        compoundNameTextField.setText("Jammeh Kunda");
        townTextField.setText("Santoto");
    }

    @FXML
    protected void onCreateHospitalButtonClick() {
        String name = nameInput();
        String size = sizeInput();
        Integer leadDoctorIndex = leadDoctorIndexInput();

        String emailAddress = emailAddressInput();
        String phoneNumber = phoneNumberInput();
        String relevantLink = relevantLinkInput();

        String compoundName = compoundNameInput();
        String town = townInput();
        Integer regionIndex = regionIndexInput();

        String createDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String updateDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        String otherDetails = otherDetailsInput();


        System.out.println(leadDoctorIndex);



//        List<Object> fieldsList = List.of(
//            "name", "size",
//            "email_address", "phone_number", "relevant_link",
//            "compound", "town", "regions_id",
//            "created_at", "updated_at", "other_details"
//        );
//
//        List<Object> valuesList = List.of(
//            "Niumi Bali", "Small",
//            "bailo@gmail.com", "07506259330", "https://bailo.com/",
//            "Jallow kunda", "Brikama", 1,
//            "1986-08-21", "1986-08-21", "I am chilling"
//        );
//
//        (new Hospital())
//            .getDatabase()
//            .fields(fieldsList)
//            .values(valuesList)
//            .insert();
    }
}
