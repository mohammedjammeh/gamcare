package com.projects.gamcare.controllers.patient;

import com.projects.gamcare.core.Hash;
import com.projects.gamcare.core.TimeTool;
import com.projects.gamcare.models.Hospital;
import com.projects.gamcare.models.User;
import javafx.fxml.FXML;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.List;

public class Create extends CreateFields {

    public void initialize() {
        super.initialize();
        firstNameTextField.setText("Manjiki");
        middleNameTextField.setText("Mbinki");
        lastNameTextField.setText("Jammeh");

        placeOfBirthTextField.setText("Tallinding");
        dateOfBirthPicker.setValue(LocalDate.of(2022, 1, 20));

        heightTextField.setText("10");
        weightTextField.setText("10");

        emailAddressTextField.setText("manjikijamme@yahoo.com");
        phoneNumberTextField.setText("07506259330");
        relevantLinkTextField.setText("https://www.google.co.uk/");

        compoundNameTextField.setText("Jammeh Kunda");
        townTextField.setText("Bali");
    }

    @FXML
    protected void onCreatePatientButtonClick() throws NoSuchAlgorithmException {
        String firstName = getFirstName();
        String middleName = getMiddleName();
        String lastName = getLastName();

        Integer titleIndex = getTitleIndex();
        Integer genderIndex = getGenderIndex();
        Integer tribeIndex = getTribeIndex();

        Integer hospitalIndex = getHospitalIndex();
        String placeOfBirth = getPlaceOfBirth();
        LocalDate dateOfBirth = getDateOfBirth();

        String weight = getWeight();
        String height = getHeight();
        Integer bloodTypeIndex = getBloodTypeIndex();

        String emailAddress = getEmailAddress();
        String phoneNumber = getPhoneNumber();
        String relevantLink = getRelevantLink();

        String compoundName = getCompoundName();
        String town = getTown();
        Integer regionIndex = getRegionIndex();

        String otherDetails = getOtherDetails();

        byte[] salt = Hash.createSalt();
        String hash = Hash.generate("password123", salt);

        (new User())
            .insert(userFieldsList(), userValuesList());
    }

    private List<Object> userFieldsList() {
        return List.of(
//            "name", "size",
//            "email_address", "phone_number", "relevant_link",
//            "compound", "town", "regions_id",
//            "created_at", "updated_at", "other_details"
        );
    }

    private List<Object> userValuesList() {
//        String newDate = TimeTool.newDate();
//        Integer regionId = regionIndexInput() + 1;

        return List.of(
//            nameInput(), sizeInput(),
//            emailAddressInput(), phoneNumberInput(), relevantLinkInput(),
//            compoundNameInput(), townInput(), regionId,
//            newDate, newDate, otherDetailsInput()
        );
    }
}
