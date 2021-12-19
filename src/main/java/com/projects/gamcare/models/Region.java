package com.projects.gamcare.models;

import com.projects.gamcare.interfaces.Model;
import com.projects.gamcare.models.main.BaseModel;

public class Region extends BaseModel implements Model {
    protected final String table = "regions";

    public String getTableName() {
        return table;
    }
}
