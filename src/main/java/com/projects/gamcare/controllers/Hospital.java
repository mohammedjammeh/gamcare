package com.projects.gamcare.controllers;

import javafx.fxml.FXML;

public class Hospital {

    @FXML
    protected void onHospitalsButtonClick() {
        System.out.println("You are now looking at hospitals.");
    }

    @FXML
    protected void onMyProfileButtonClick() {
        System.out.println("You are now looking at your profile.");
    }

    @FXML
    protected void onLogOutButtonClick() {
        System.out.println("You are now logged out.");
    }



    // Index
    @FXML
    protected void onAddHospitalButtonClick() {
        System.out.println("You have now added a hospital.");
    }

    @FXML
    protected void onShowHospitalButtonClick() {
        System.out.println("You can now see a hospital.");
    }




    // Show
    @FXML
    protected void onAddPatientButtonClick() {
        System.out.println("You have now added a patient.");
    }

    @FXML
    protected void onShowPatientButtonClick() {
        System.out.println("You can now see a patient.");
    }

    @FXML
    protected void onEditPatientButtonClick() {
        System.out.println("You can now edit a patient.");
    }

    @FXML
    protected void onDeletePatientButtonClick() {
        System.out.println("You can now delete a patient.");
    }



    @FXML
    protected void onAddDoctorButtonClick() {
        System.out.println("You have now added a doctor.");
    }

    @FXML
    protected void onShowDoctorButtonClick() {
        System.out.println("You can now see a doctor.");
    }

    @FXML
    protected void onEditDoctorButtonClick() {
        System.out.println("You can now edit a doctor.");
    }

    @FXML
    protected void onDeleteDoctorButtonClick() {
        System.out.println("You can now delete a doctor.");
    }
}
