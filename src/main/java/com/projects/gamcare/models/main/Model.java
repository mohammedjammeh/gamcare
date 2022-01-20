package com.projects.gamcare.models.main;

import com.projects.gamcare.core.Database;
import com.projects.gamcare.core.TimeTool;
import com.projects.gamcare.interfaces.ModelInterface;
import com.projects.gamcare.models.*;

import java.util.*;

public class Model implements ModelInterface {
    protected Database database;
    protected String table;
    public Map<String, Object> attributes = new HashMap<>();

    public Model() {
        database = new Database(this);
    }

    public Model setTableName(String table) {
        this.table = table;

        return this;
    }

    public String getTableName() {
        return table;
    }


    /**
     * Attributes Methods
     */
    public Object getAttribute(String name) {
        return attributes.get(name);
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
        return getAttribute("first_name") + " "
            + getAttribute("middle_name") + " "
            + getAttribute("last_name");
    }

    public String nameAttribute() {
        return (String) attributes.get("name");
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
     * Override Methods
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Model other = (Model) obj;
        return Objects.equals(idAttribute(), other.idAttribute());
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAttribute());
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
            .where("id", getAttribute("regions_id"))
            .first();
    }

    public Title getTitle() {
        return (Title) (new Title())
            .where("id", getAttribute("titles_id"))
            .first();
    }

    public Gender getGender() {
        return (Gender) (new Gender())
            .where("id", getAttribute("genders_id"))
            .first();
    }

    public Tribe getTribe() {
        return (Tribe) (new Tribe())
            .where("id", getAttribute("tribes_id"))
            .first();
    }
}
