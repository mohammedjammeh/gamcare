package com.projects.gamcare.models.main;

import com.projects.gamcare.core.Database;
import com.projects.gamcare.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {
    protected Database database;

    public Map<String, Object> attributes = new HashMap<>();

    public Model() {
        database = new Database(this);
    }

    public List<String> getNames() {
//        return select(List.of("*"))
//            .orderBy("id")
//            .getAll()
//            .stream()
//            .map(DatabaseModel::getNameAttribute)
//            .toList();

        return new ArrayList<>();
    }

    public String getTableName() {
        return null;
    }
}
