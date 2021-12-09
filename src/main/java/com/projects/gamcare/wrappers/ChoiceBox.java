package com.projects.gamcare.wrappers;

import com.projects.gamcare.core.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class ChoiceBox extends javafx.scene.control.ChoiceBox<String> {
    public void addItemsFrom(String tableName) {
        String sql = "SELECT * FROM " + tableName + " ORDER BY id";
        DB db = new DB(sql);
        db.getResults();



        try {
            Connection dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamcare", "root", "3aj3!96wMWeyU9&z");

            PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM " + tableName + " ORDER BY id");
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                String title = capitalizeString(results.getString("name"));
                this.getItems().add(title);

                if(results.isFirst())
                    this.setValue(title);
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
