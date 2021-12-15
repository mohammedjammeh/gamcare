package com.projects.gamcare.models;

import com.projects.gamcare.core.DB;
import com.projects.gamcare.models.main.BaseModel;

import java.util.List;
import java.util.Map;

public class User extends BaseModel {
    private static final String table = "users";

    public static DB selectQuery;

    public static DB where(String column, String value) {
        if(selectQuery == null) {
            selectQuery = database().select(List.of("*")).from(table);
        }

        return selectQuery.where(column, "=", value);
    }
}
