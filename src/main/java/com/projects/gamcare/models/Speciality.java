package com.projects.gamcare.models;

import com.projects.gamcare.interfaces.Model;
import com.projects.gamcare.models.main.BaseModel;

public class Speciality extends BaseModel implements Model {
    protected final String table = "specialities";

    public String getTableName() {
        return table;
    }
}
