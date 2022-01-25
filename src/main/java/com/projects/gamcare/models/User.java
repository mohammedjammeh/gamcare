package com.projects.gamcare.models;

import com.projects.gamcare.core.SceneTool;
import com.projects.gamcare.enums.UserType;
import com.projects.gamcare.interfaces.ModelInterface;
import com.projects.gamcare.models.main.Model;
import com.projects.gamcare.models.main.ProfileUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User extends ProfileUser implements ModelInterface {
    protected final String table = "users";

    public String getTableName() {
        return table;
    }


    /**
     * Attributes Methods
     */
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

    public Boolean isNotAdmin() {
        return ! isAdmin();
    }

    public void switchToAfterLoginResource() {
        if (isPatient()) {
            SceneTool.switchToProfile(afterLoginResourceName(), this, this);
            return;
        }

        SceneTool.switchTo(afterLoginResourceName(), this);
    }

    public String afterLoginResourceName() {
        Map<String, String> resourceNames = new HashMap<>();

        resourceNames.put(UserType.ADMIN.name(), "hospital/index");
        resourceNames.put(UserType.DOCTOR.name(), "hospital/index");
        resourceNames.put(UserType.PATIENT.name(), "patient/show");

        return resourceNames.get(typeAttribute());
    }

    public String profileResourceName() {
        Map<String, String> resourceNames = new HashMap<>();

        resourceNames.put(UserType.ADMIN.name(), "admin/show");
        resourceNames.put(UserType.DOCTOR.name(), "doctor/show");
        resourceNames.put(UserType.PATIENT.name(), "patient/show");

        return resourceNames.get(typeAttribute());
    }


    /**
     * Query Methods
     */
    public byte[] getSalt() {
        return database
            .select(List.of("salt"))
            .where("id", idAttribute())
            .getBytes();
    }

    public List<Model> getHospitals() {
        if (isAdmin()) {
            return (new Hospital()).getDatabase().getAll();
        }

        if (isDoctor()) {
            return this.getDoctor().getHospitals();
        }

        return new ArrayList<>();
    }
}
