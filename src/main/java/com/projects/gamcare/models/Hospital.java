package com.projects.gamcare.models;

import com.projects.gamcare.core.Database;
import com.projects.gamcare.interfaces.ModelInterface;
import com.projects.gamcare.models.main.Model;

import java.util.List;

public class Hospital extends Model implements ModelInterface {
    protected final String table = "hospitals";

    public String getTableName() {
        return table;
    }

    public void prepareQuery() {
        if (database.sqlIsNull()) {
            database.select(List.of("*"));
        }
    }


    /**
     * Query Methods
     */
    public Doctor getLeadDoctor() {
        return (Doctor) (new Doctor())
            .getDatabase()
            .select(List.of("*"))
            .with("users")
            .withMany("hospitals", "hospitals_doctors")
            .where("hospitals_doctors.lead_doctor", "=", 1)
            .where("doctors.id", "=", idAttribute())
            .first();
    }
}
