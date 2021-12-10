package com.projects.gamcare.wrappers;

import com.projects.gamcare.core.DB;
import com.projects.gamcare.core.StringTool;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ChoiceBox extends javafx.scene.control.ChoiceBox<String> {
    public void addItemsFrom(String table) {
        List<Map<String, String>> dbItems = new DB()
            .select("name")
            .from(table)
            .orderBy("id")
            .get();

        List<String> items = dbItems
            .stream()
            .map(item -> item.get("name"))
            .toList();

        this.getItems().addAll(items);
        this.setValue(items.get(0));
    }

    public void addEnumsFrom(Class<?> enumClass) {
        List<String> items = Arrays
            .stream(enumClass.getEnumConstants())
            .map(Object::toString)
            .map(StringTool::capitalise)
            .toList();

        this.getItems().addAll(items);
        this.setValue(items.get(0));
    }
}
