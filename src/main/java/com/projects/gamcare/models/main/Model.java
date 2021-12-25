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


    /**
     * Query Methods
     */
    public Model first() {
        return (Model) database.first();
    }

    public Model last() {
        return (Model) database.last();
    }

    public Model where(String column, String value) {
        database.where(column, "=", value);

        return this;
    }

    public byte[] getSalt() {
        return database
            .select(List.of("salt"))
            .where("id", "=", this.attributes.get("id"))
            .getBytes();
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


    /**
     * Attributes Methods
     */
    public String getFullName() {
        return this.attributes.get("first_name") + " "
            + this.attributes.get("last_name");
    }

    public String getName() {
        return (String) this.attributes.get("name");
    }
}
