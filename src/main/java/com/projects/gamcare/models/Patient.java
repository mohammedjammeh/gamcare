package com.projects.gamcare.models;

import com.projects.gamcare.interfaces.ModelInterface;
import com.projects.gamcare.models.main.Model;

import java.text.DecimalFormat;

public class Patient extends Model implements ModelInterface {
    protected final String table = "patients";

    public String getTableName() {
        return table;
    }

    /**
     * General Methods
     */
    public String getScore() {
        double randomNumber = Math.random();

        return String.valueOf(new DecimalFormat("#.#").format(randomNumber));
    }
}
