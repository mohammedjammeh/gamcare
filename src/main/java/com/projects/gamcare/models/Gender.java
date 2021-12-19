package com.projects.gamcare.models;

import com.projects.gamcare.interfaces.Model;
import com.projects.gamcare.models.main.BaseModel;

public class Gender extends BaseModel implements Model {
    protected final String table = "genders";

    public String getTableName() {
        return table;
    }
}
