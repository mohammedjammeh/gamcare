package com.projects.gamcare.wrappers;

import com.projects.gamcare.core.DB;

import java.util.List;
import java.util.Map;

public class ChoiceBox extends javafx.scene.control.ChoiceBox<String> {
    public void addItemsFrom(String table) {
        List<Map<String, String>> dbItems = new DB()
            .select("name")
            .from(table)
            .orderBy("id")
            .query()
            .getResults();

        List<String> items = dbItems
            .stream()
            .map(result -> result.get("name"))
            .toList();

        this.getItems().addAll(items);
        this.setValue(items.get(0));
    }
}
