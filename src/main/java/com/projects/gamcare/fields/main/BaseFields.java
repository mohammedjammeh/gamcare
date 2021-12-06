package com.projects.gamcare.fields.main;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BaseFields {
    @FXML
    private VBox errorBox;


    public void hideErrorBox() {
        errorBox.setVisible(false);
        errorBox.setManaged(false);
    }

    public void addItemsFromTableToChoiceBox(String table, ChoiceBox<String> choiceBox) {
        try {
            Connection dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamcare", "root", "3aj3!96wMWeyU9&z");

            PreparedStatement statement = dbConnection.prepareStatement("SELECT name FROM " + table + " ORDER BY id");
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                String title = capitalizeString(results.getString("name"));
                choiceBox.getItems().add(title);

                if(results.isFirst())
                    choiceBox.setValue(title);
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void addItemsFromTableToListView(String table, ListView<String> listView) {
        try {
            Connection dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamcare", "root", "3aj3!96wMWeyU9&z");

            PreparedStatement statement = dbConnection.prepareStatement("SELECT name FROM " + table + " ORDER BY id");
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                String name = capitalizeString(results.getString("name"));
                listView.getItems().add(name);
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
