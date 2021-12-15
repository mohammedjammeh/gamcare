package com.projects.gamcare.models.main;

import com.projects.gamcare.core.DB;

import java.util.ArrayList;
import java.util.List;

public class BaseModel {
    public static DB database() {
        return new DB();
    }

    public static List<String> getNames(String table) {
//        return new DB()
//            .select(List.of("*"))
//            .from(table)
//            .orderBy("id")
//            .get()
//            .stream()
//            .map(item -> item.get("name"))
//            .toList();
        return new ArrayList<>();
    }
}
