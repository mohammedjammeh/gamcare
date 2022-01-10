package com.projects.gamcare.models.main;

import com.projects.gamcare.core.TimeTool;

public class ProfileUser extends Model {
    /**
     * General Methods
     */
    public String dateOfBirthDisplay() {
        return TimeTool.dateOfBirthDisplay(dateOfBirthAttribute());
    }
}
