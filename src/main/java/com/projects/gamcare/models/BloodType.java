package com.projects.gamcare.models;

import com.projects.gamcare.models.main.BaseModel;

import java.util.List;

public class BloodType extends BaseModel {
    private static final String table = "blood_types";

    public static List<String> getItems() {
        return getNames(table);
    }
}
