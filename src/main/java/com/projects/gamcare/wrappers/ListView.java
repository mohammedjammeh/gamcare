package com.projects.gamcare.wrappers;

import com.projects.gamcare.core.DB;
import javafx.geometry.Orientation;
import javafx.scene.control.SelectionMode;
import java.util.List;
import java.util.Map;

public class ListView extends javafx.scene.control.ListView<String> {
    public ListView() {
        super();
        this.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.setOrientation(Orientation.VERTICAL);
    }

    public void addItemsFrom(String table) {
        DB db = new DB().select("name").from(table).orderBy("id").query();
        List<Map<String, String>> results = db.getResults();

        for (Map<String, String> result: results) {
            String name = result.get("name");
            this.getItems().add(name);
        }
    }
}
