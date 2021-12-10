package com.projects.gamcare.core;

import java.io.FileInputStream;
import java.sql.*;
import java.util.*;

public class DB {
    public String sql;
    private Connection dbConnection;

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

    public List<Map<String, String>> get() {
        List<Map<String, String>> results = new ArrayList<>();

        try {
            ResultSet queryResults = this.query();
            while (queryResults.next()) {
                Map<String, String> row = getRowValues(queryResults);
                results.add(row);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return results;
    }

    private ResultSet query() throws SQLException {
        PreparedStatement statement = dbConnection.prepareStatement(sql);

        return statement.executeQuery();
    }


    private Map<String, String> getRowValues(ResultSet queryResults) throws SQLException {
        Map<String, String> row = new HashMap<>();

        for (String columnName : getColumnNames(queryResults)) {
            row.put(columnName, queryResults.getString(columnName));
        }

        return row;
    }


    private Collection<String> getColumnNames(ResultSet queryResults) throws SQLException {
        Collection<String> columnNames = new ArrayList<>();
        ResultSetMetaData metaData = queryResults.getMetaData();

        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            columnNames.add(metaData.getColumnName(i));
        }

        return columnNames;
    }
}
