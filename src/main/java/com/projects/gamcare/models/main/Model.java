package com.projects.gamcare.models.main;

import com.projects.gamcare.core.Database;
import com.projects.gamcare.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {
    protected Database database;

    public Map<String, Object> attributes = new HashMap<>();

    public Model() {
        database = new Database(this);
    }

    public String getTableName() {
        return null;
    }





    public User first() {
        return (User) database.first();
    }

    public User last() {
        return (User) database.last();
    }





    public String getFullName() {
        return this.attributes.get("first_name") + " "
            + this.attributes.get("last_name");
    }

    public String getName() {
        return (String) this.attributes.get("name");
    }





    public List<String> getAllNames() {
        return database.
            select(List.of("*"))
            .orderBy("id")
            .getAll()
            .stream()
            .map(Model::getName)
            .toList();
    }
}
