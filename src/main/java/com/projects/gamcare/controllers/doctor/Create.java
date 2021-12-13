package com.projects.gamcare.controllers.doctor;

import com.projects.gamcare.fields.DoctorFields;
import javafx.fxml.FXML;
import java.time.LocalDate;
import java.util.List;

public class Create extends DoctorFields {
    @FXML
    protected void onAddDoctorsButtonClick() {
        List<Integer> studentDoctorsIndices = getStudentDoctorsIndices();
        List<Integer> juniorDoctorsIndices = getJuniorDoctorsIndices();
        List<Integer> seniorDoctorsIndices = getSeniorDoctorsIndices();
    }


    @FXML
    protected void onCreateDoctorButtonClick() {
        String firstName = getFirstName();
        String middleName = getMiddleName();
        String lastName = getLastName();

        Integer titleIndex = getTitleIndex();
        Integer genderIndex = getGenderIndex();
        Integer tribeIndex = getTribeIndex();

        String university = getUniversity();
        String fieldOfStudy = getFieldOfStudy();
        String careerLevel = getCareerLevel();

        Integer specialityIndex = getSpecialityIndex();
        String placeOfBirth = getPlaceOfBirth();
        LocalDate dateOfBirth = getDateOfBirth();

        String emailAddress = getEmailAddress();
        String phoneNumber = getPhoneNumber();
        String relevantLink = getRelevantLink();

        String compoundName = getCompoundName();
        String town = getTown();
        Integer regionIndex = getRegionIndex();

        String otherDetails = getOtherDetails();
    }
}
