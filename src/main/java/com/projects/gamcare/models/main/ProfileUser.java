package com.projects.gamcare.models.main;

import com.projects.gamcare.core.TimeTool;
import com.projects.gamcare.models.Doctor;
import com.projects.gamcare.models.Patient;
import com.projects.gamcare.models.User;

public class ProfileUser extends Model {
    /**
     * General Methods
     */
    public String dateOfBirthDisplay() {
        return TimeTool.dateOfBirthDisplay(dateOfBirthAttribute());
    }

    public Doctor getDoctor() {
        return (Doctor) (new Doctor()).where("id", getAttribute("doctors_id")).first();
    }

    public Patient getPatient() {
        return (Patient) (new Patient()).where("id", getAttribute("patients_id")).first();
    }

    public User getUser() {
        return (User) (new User()).where("patients_id", getAttribute("patients_id")).first();
    }
}
