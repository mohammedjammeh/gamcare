package com.projects.gamcare.models;

import com.projects.gamcare.interfaces.ModelInterface;
import com.projects.gamcare.models.main.Model;

public class Region extends Model implements ModelInterface {
    protected final String table = "regions";

    public String getTableName() {
        return table;
    }
}
