package com.projects.gamcare.wrappers;

import com.projects.gamcare.core.DB;

import java.util.List;
import java.util.Map;

public class ChoiceBox extends javafx.scene.control.ChoiceBox<String> {
    public void addItemsFrom(String table) {
        DB db = new DB().select("name").from(table).orderBy("id").query();
        List<Map<String, String>> results = db.getResults();

        for (Map<String, String> result: results) {
            String name = result.get("name");
            this.getItems().add(name);

            if(isItemFirst(result, results))
                this.setValue(name);
        }
    }

    private boolean isItemFirst(Map<String, String> result, List<Map<String, String>> results) {
        return results.indexOf(result) == 0;
    }
}
