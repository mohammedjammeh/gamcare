package com.projects.gamcare.controllers.admin;

import com.projects.gamcare.controllers.admin.parents.Fields;
import com.projects.gamcare.core.SceneTool;
import com.projects.gamcare.enums.UserType;
import com.projects.gamcare.models.User;
import com.projects.gamcare.models.main.ProfileUser;
import javafx.fxml.FXML;

import java.util.TreeMap;

public class Edit extends Fields {
    private ProfileUser profileUser;

    public void setUpEditForm() {
        setInputData(profileUser);
    }

    @FXML
    protected void onUpdateAdminButtonClick() {
        TreeMap<String, Object> data = new TreeMap<>();
        data.put("type", UserType.ADMIN.name());

        profileUser.update(newUserData(data));
        ProfileUser profileUserFromDB = (ProfileUser) (new User()).where("id", profileUser.idAttribute()).first();

        SceneTool.switchToProfile(getAuthUser().profileResourceFolderName(), getAuthUser(), profileUserFromDB);
        SceneTool.closeWindow(firstNameTextField);
    }

    /**
     * Getters & Setters
     */
    public void setProfileUser(ProfileUser profileUser) {
        this.profileUser = profileUser;
    }
}
