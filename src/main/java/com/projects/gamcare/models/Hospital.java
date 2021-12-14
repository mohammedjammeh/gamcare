package com.projects.gamcare.models;

import com.projects.gamcare.models.main.BaseModel;

import java.util.List;

public class Hospital extends BaseModel {
    private static final String table = "hospitals";

    public static List<String> getItems() {
        return getNames(table);
    }
}
