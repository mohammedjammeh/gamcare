package com.projects.gamcare.models;

import com.projects.gamcare.models.main.BaseModel;

public class User extends BaseModel {
    private static final String table = "users";

    public String getTableName() {
        return table;
    }

    public User newInstance() {
        return new User();
    }

    public User where(String column, String value) {
        super.where(column, "=", value);

        return this;
    }

    public User first() {
        return (User) super.first();
    }

    public User last() {
        return (User) super.last();
    }
}
