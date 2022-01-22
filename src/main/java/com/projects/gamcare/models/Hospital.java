package com.projects.gamcare.models;

import com.projects.gamcare.interfaces.ModelInterface;
import com.projects.gamcare.models.main.Model;

import java.util.List;
import java.util.Map;

public class Hospital extends Model implements ModelInterface {
    protected final String table = "hospitals";

    public String getTableName() {
        return table;
    }


    /**
     * Query Methods
     */
    public Doctor getLeadDoctor() {
        return (Doctor) (new Doctor())
            .with("users")
            .withMany("hospitals", "hospitals_doctors")
            .where("hospitals_doctors.lead_doctor", "=", 1)
            .where("hospitals.id", "=", idAttribute())
            .first();
    }

    public List<Model> getDoctors() {
        return (new Doctor())
            .with("users")
            .withMany("hospitals", "hospitals_doctors")
            .where("hospitals.id", "=", idAttribute())
            .getAll();
    }

    public List<Model> getPatients() {
        return (new Patient())
            .with("users")
            .where("hospitals_id", "=", idAttribute())
            .getAll();
    }
}
