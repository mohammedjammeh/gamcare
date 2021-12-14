package com.projects.gamcare.models.main;

import com.projects.gamcare.core.DB;

public class BaseModel {
    public static DB database() {
        return new DB();
    }
}
