package com.projects.gamcare.models.main;

import com.projects.gamcare.core.TimeTool;
import com.projects.gamcare.models.Doctor;
import com.projects.gamcare.models.Patient;

public class ProfileUser extends Model {
    /**
     * Attributes Methods
     */
    public Integer doctorIdAttribute() {
        return (Integer) this.attributes.get("doctors_id");
    }

    public Integer patientIdAttribute() {
        return (Integer) this.attributes.get("patients_id");
    }


    /**
     * General Methods
     */
    public String dateOfBirthDisplay() {
        return TimeTool.dateOfBirthDisplay(dateOfBirthAttribute());
    }


    /**
     * Query Methods
     */
    public Doctor getDoctor() {
        return (Doctor) (new Doctor()).where("id", doctorIdAttribute()).first();
    }

    public Patient getPatient() {
        return (Patient) (new Patient()).where("id", patientIdAttribute()).first();
    }
}
