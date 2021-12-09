package com.projects.gamcare.core;

import java.io.FileInputStream;
import java.sql.*;
import java.util.*;

public class DB {
    public String sql;
    private Connection dbConnection;
    private ResultSet rawResults;

    public DB() {
        try {
            Properties properties = new Properties();
            FileInputStream propertiesFile = new FileInputStream("src/main/java/com/projects/gamcare/config/config.properties");
            properties.load(propertiesFile);

            dbConnection = DriverManager.getConnection(
                properties.getProperty("database_url"),
                properties.getProperty("database_user"),
                properties.getProperty("database_password")
            );
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public DB select(String column) {
        sql = "SELECT " + column + " ";

        return this;
    }

    public DB from(String table) {
        sql += "FROM " + table + " ";

        return this;
    }

    public DB orderBy(String column) {
        sql += "ORDER BY " + column;

        return this;
    }

    public DB query() {
        try {
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            rawResults = statement.executeQuery();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return this;
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
