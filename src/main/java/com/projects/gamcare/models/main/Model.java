package com.projects.gamcare.models.main;

import com.projects.gamcare.core.Database;
import com.projects.gamcare.core.TimeTool;
import com.projects.gamcare.models.Region;

import java.util.*;

import static java.util.Calendar.*;
import static java.util.Calendar.DATE;

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
    public String getAttribute(String name) {
        return (String) attributes.get(name);
    }

    public Integer idAttribute() {
        return (Integer) attributes.get("id");
    }

    public String firstNameAttribute() {
        return (String) attributes.get("first_name");
    }

    public String middleNameAttribute() {
        return (String) attributes.get("middle_name");
    }

    public String lastNameAttribute() {
        return (String) attributes.get("last_name");
    }

    public String emailAttribute() {
        return (String) attributes.get("email_address");
    }

    public String fullNameAttribute() {
        return attributes.get("first_name") + " "
            + attributes.get("last_name");
    }

    public String nameAttribute() {
        return (String) attributes.get("name");
    }

    public Integer regionIdAttribute() {
        return (Integer) attributes.get("regions_id");
    }

    public String age() {
        return TimeTool.yearsSince(dateOfBirthAttribute()) + " years";
    }

    public Date dateOfBirthAttribute() {
        return (Date) attributes.get("date_of_birth");
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
    public Database with(String anotherTableName) {
        return database.with(anotherTableName);
    }

    public Model first() {
        return (Model) database.first();
    }

    public Model last() {
        return (Model) database.last();
    }

    public List<Model> getAll() {
        return database.getAll();
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
