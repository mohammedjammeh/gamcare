package com.projects.gamcare.fields.main;

import com.projects.gamcare.core.DB;
import com.projects.gamcare.core.StringTool;
import com.projects.gamcare.enums.DoctorLevel;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.List;

public class BaseFields {
    @FXML
    protected VBox errorBox;

    public void initialize() {
        errorBox.setVisible(false);
        errorBox.setManaged(false);
    }

    public List<String> getDatabaseItems(String table) {
        return new DB()
            .select(List.of("*"))
            .from(table)
            .orderBy("id")
            .get()
            .stream()
            .map(item -> item.get("name"))
            .toList();
    }

    public List<String> getDatabaseItems(String mainTable, String withTable, String whereColumn, String whereValue) {
        return new DB()
            .select(List.of("*"))
            .from(mainTable)
            .with(withTable)
            .where(whereColumn, "=", whereValue)
            .orderBy("first_name")
            .get()
            .stream()
                .distinct()
            .map(item -> item.get("first_name") + " " + item.get("last_name"))
            .toList();
    }

    public List<String> getEnumItems(Class<?> enumClass) {
        return Arrays
            .stream(enumClass.getEnumConstants())
            .map(Object::toString)
            .map(StringTool::capitalise)
            .toList();
    }
}
