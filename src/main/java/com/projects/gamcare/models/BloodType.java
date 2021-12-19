package com.projects.gamcare.models;

import com.projects.gamcare.interfaces.Model;
import com.projects.gamcare.models.main.BaseModel;

public class BloodType extends BaseModel implements Model {
    protected final String table = "blood_types";

    public String getTableName() {
        return table;
    }
}
