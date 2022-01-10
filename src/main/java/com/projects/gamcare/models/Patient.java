package com.projects.gamcare.models;

import com.projects.gamcare.interfaces.ModelInterface;
import com.projects.gamcare.models.main.ProfileUser;

import java.text.DecimalFormat;

public class Patient extends ProfileUser implements ModelInterface {
    protected final String table = "patients";

    public String getTableName() {
        return table;
    }


    /**
     * General Methods
     */
    public String weightAttribute() {
        return getAttribute("weight") + " kg";
    }

    public String heightAttribute() {
        return getAttribute("height") + " cm";
    }


    /**
     * General Methods
     */
    public String getScore() {
        double randomNumber = Math.random();

        return String.valueOf(new DecimalFormat("#.#").format(randomNumber));
    }


    /**
     * Query Methods
     */
    public Hospital getHospital() {
        return (Hospital) (new Hospital())
            .where("id", getAttribute("hospitals_id"))
            .first();
    }

    public BloodType getBloodType() {
        return (BloodType) (new BloodType())
            .where("id", getAttribute("blood_types_id"))
            .first();
    }
}
