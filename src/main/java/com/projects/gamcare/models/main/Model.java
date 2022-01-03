package com.projects.gamcare.models.main;

import com.projects.gamcare.core.Database;
import com.projects.gamcare.models.Region;

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

    public void prepareQuery() {
        if (database.sqlIsNull()) {
            database.select(List.of("*"));
        }
    }


    /**
     * Attributes Methods
     */
    public Integer idAttribute() {
        return (Integer) this.attributes.get("id");
    }

    public String fullNameAttribute() {
        return this.attributes.get("first_name") + " "
            + this.attributes.get("last_name");
    }

    public String nameAttribute() {
        return (String) this.attributes.get("name");
    }

    public Integer regionIdAttribute() {
        return (Integer) this.attributes.get("regions_id");
    }


    /**
     * General Methods
     */
    public Database getDatabase() {
        return database;
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

    public Model where(String column, Object value) {
        database.where(column, "=", value);

        return this;
    }

    public List<String> getAllNames() {
        return database.
            select(List.of("*"))
            .orderBy("id")
            .getAll()
            .stream()
            .map(Model::nameAttribute)
            .toList();
    }

    public Region getRegion() {
        return (Region) (new Region())
            .getDatabase()
            .where("id", "=", regionIdAttribute())
            .first();
    }
}