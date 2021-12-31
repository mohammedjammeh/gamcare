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

    public void prepareQuery() {
        if (database.sqlIsNull()) {
            database.select(List.of("*"));
        }
    }


    /**
     * Attributes Methods
     */
    public Integer idAttribute() {
        return super.idAttribute();
    }

    public String typeAttribute() {
        return (String) this.attributes.get("type");
    }


    /**
     * General Methods
     */
    public Boolean isAdmin() {
        return typeAttribute().equals(UserType.ADMIN.name());
    }

    public Boolean isDoctor() {
        return typeAttribute().equals(UserType.DOCTOR.name());
    }

    public Boolean isPatient() {
        return typeAttribute().equals(UserType.PATIENT.name());
    }

    public String afterLoginResourceName() {
        Map<String, String> resourceNames = new HashMap<>();

        resourceNames.put(UserType.ADMIN.name(), "hospital/index");
        resourceNames.put(UserType.DOCTOR.name(), "hospital/index");
        resourceNames.put(UserType.PATIENT.name(), "patient/show");

        return resourceNames.get(typeAttribute());
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

    public User where(String column, Object value) {
        return (User) super.where(column, value);
    }

    public byte[] getSalt() {
        return database
            .select(List.of("salt"))
            .where("id", "=", idAttribute())
            .getBytes();
    }

    public Doctor getDoctor() {
        return (new Doctor()).where("id", idAttribute()).first();
    }
}
