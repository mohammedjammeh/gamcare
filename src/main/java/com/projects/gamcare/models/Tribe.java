package com.projects.gamcare.models;

import com.projects.gamcare.interfaces.ModelInterface;
import com.projects.gamcare.models.main.Model;

public class Tribe extends Model implements ModelInterface {
    protected final String table = "tribes";

    public String getTableName() {
        return table;
    }
}
