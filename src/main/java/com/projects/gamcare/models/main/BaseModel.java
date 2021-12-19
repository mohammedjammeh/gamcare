package com.projects.gamcare.models.main;

import com.projects.gamcare.core.DatabaseModel;

import java.util.List;

public class BaseModel extends DatabaseModel {
    public List<String> getNames() {
        return select(List.of("*"))
            .orderBy("id")
            .getAll()
            .stream()
            .map(DatabaseModel::getNameAttribute)
            .toList();
    }
}
