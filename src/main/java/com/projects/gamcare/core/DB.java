package com.projects.gamcare.core;

import java.io.FileInputStream;
import java.sql.*;
import java.util.*;

public class DB {
    private Boolean error;
    private ResultSet rawResults;

    public DB(String sql) {
        try {
            Properties properties = new Properties();
            FileInputStream propertiesFile = new FileInputStream("src/main/java/com/projects/gamcare/config/config.properties");
            properties.load(propertiesFile);

            Connection dbConnection = DriverManager.getConnection(
                properties.getProperty("database_url"),
                properties.getProperty("database_user"),
                properties.getProperty("database_password")
            );

            PreparedStatement statement = dbConnection.prepareStatement(sql);
            rawResults = statement.executeQuery();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public Boolean getError() {
        return error;
    }

    public List<Map<String, String>> getResults() {
        List<Map<String, String>> results = new ArrayList<>();

        try {
            while (rawResults.next()) {
                Map<String, String> row = new HashMap<>();
                for (String columnName : getColumnNames(rawResults)) {
                    row.put(columnName, rawResults.getString(columnName));
                }
                results.add(row);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return results;
    }

    private Collection<String> getColumnNames(ResultSet rawResults) {
        Collection<String> columnNames = new ArrayList<>();

        try {
            ResultSetMetaData metaData = rawResults.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                columnNames.add(metaData.getColumnName(i));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return columnNames;
    }
}
