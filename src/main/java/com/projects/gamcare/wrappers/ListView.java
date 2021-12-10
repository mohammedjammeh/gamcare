package com.projects.gamcare.wrappers;

import com.projects.gamcare.core.DB;
import javafx.geometry.Orientation;
import javafx.scene.control.SelectionMode;
import java.util.List;

public class ListView extends javafx.scene.control.ListView<String> {
    public ListView() {
        super();
        this.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.setOrientation(Orientation.VERTICAL);
    }

    public void addItemsFrom(String table) {
        var dbItems = new DB()
            .select("name")
            .from(table)
            .orderBy("id")
            .get();

        List<String> items = dbItems
            .stream()
            .map(item -> item.get("name"))
            .toList();

        this.getItems().addAll(items);
    }
}
