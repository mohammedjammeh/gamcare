package com.projects.gamcare.models;

import com.projects.gamcare.models.main.BaseModel;

import java.util.List;

public class Gender extends BaseModel {
    private static final String table = "genders";

    public static List<String> getItems() {
        return getNames(table);
    }
}
