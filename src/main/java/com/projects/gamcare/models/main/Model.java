package com.projects.gamcare.models.main;

import com.projects.gamcare.core.Database;
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

    public List<String> getAllNames() {
        return database.
            select(List.of("*"))
            .orderBy("id")
            .getAll()
            .stream()
            .map(Model::getNameAttribute)
            .toList();
    }

    public String getFullName() {
        return this.attributes.get("first_name") + " " + this.attributes.get("last_name");
    }

    public String getNameAttribute() {
        return (String) this.attributes.get("name");
    }

}
