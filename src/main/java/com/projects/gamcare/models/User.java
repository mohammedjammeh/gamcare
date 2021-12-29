package com.projects.gamcare.models;

import com.projects.gamcare.enums.UserType;
import com.projects.gamcare.interfaces.ModelInterface;
import com.projects.gamcare.models.main.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User extends Model implements ModelInterface {
    protected final String table = "users";

    public String getTableName() {
        return table;
    }


    /**
     * Query Methods
     */
    public User first() {
        return (User) super.first();
    }

    public User last() {
        return (User) super.last();
    }

    public User where(String column, String value) {
        return (User) super.where(column, value);
    }

    public byte[] getSalt() {
        return database
            .select(List.of("salt"))
            .where("id", "=", this.attributes.get("id"))
            .getBytes();
    }


    /**
     * General Methods
     */
    public String afterLoginResourceName() {
        Map<String, String> resourceNames = new HashMap<>();

        resourceNames.put(UserType.ADMIN.name(), "hospital/index");
        resourceNames.put(UserType.DOCTOR.name(), "doctor/show");
        resourceNames.put(UserType.PATIENT.name(), "patient/show");

        return resourceNames.get(type());
    }


    /**
     * Attributes Methods
     */
    public Boolean isAdmin() {
        return type().equals(UserType.ADMIN.name());
    }

    public Boolean isDoctor() {
        return type().equals(UserType.DOCTOR.name());
    }

    public String  type() {
        return (String) this.attributes.get("type");
    }

}
