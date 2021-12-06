package com.projects.gamcare.wrappers;

import javafx.geometry.Orientation;
import javafx.scene.control.SelectionMode;

import java.sql.*;

public class ListView extends javafx.scene.control.ListView<String> {
    public ListView() {
        super();
        this.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.setOrientation(Orientation.VERTICAL);
    }

    public void addItemsFrom(String tableName) {
        try {
            Connection dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamcare", "root", "3aj3!96wMWeyU9&z");

            PreparedStatement statement = dbConnection.prepareStatement("SELECT name FROM " + tableName + " ORDER BY id");
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                String name = capitalizeString(results.getString("name"));
                this.getItems().add(name);
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public static String capitalizeString(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }
}
