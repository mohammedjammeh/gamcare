package com.projects.gamcare.controllers.patient;

import com.projects.gamcare.core.Hash;
import com.projects.gamcare.core.TimeTool;
import com.projects.gamcare.enums.UserType;
import com.projects.gamcare.models.Patient;
import com.projects.gamcare.models.User;
import javafx.fxml.FXML;

import java.time.LocalDate;
import java.util.List;

public class Create extends CreateFields {
    private Patient newPatient;

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
    protected void onCreatePatientButtonClick() {
//        (new User())
//            .insert(userFieldsList(), userValuesList());
    }

    private List<Object> userFieldsList() {
        return List.of(
            "first_name", "middle_name", "last_name",
            "email_address", "phone_number", "relevant_link",
            "date_of_birth", "place_of_birth",
            "compound", "town",
            "type", "patients_id", "doctors_id",
            "created_at", "updated_at",
            "titles_id", "genders_id", "tribes_id", "regions_id",
            "other_details", "salt", "hash"
        );
    }

    private List<Object> userValuesList() {
        String newDate = TimeTool.newDate();
        byte[] salt = Hash.createSalt();
        String hash = Hash.generate("password123", salt);

        return List.of(
            firstNameInput(), middleNameInput(), lastNameInput(),
            emailAddressInput(), phoneNumberInput(), relevantLinkInput(),
            dateOfBirthIput(), placeOfBirthInput(),
            compoundNameInput(), townInput(),
            UserType.PATIENT.name(), 1, "",
            newDate, newDate,
            titleInputId(), genderInputId(), tribeInputId(), regionInputId(),
            otherDetailsInput(), salt, hash
        );
    }
}
