package com.projects.gamcare.models;

import com.projects.gamcare.interfaces.ModelInterface;
import com.projects.gamcare.models.main.Model;

public class User extends Model implements ModelInterface {
    protected final String table = "users";

    public String getTableName() {
        return table;
    }

    public User where(String column, String value) {
        database.where(column, "=", value);

        return this;
    }
}
