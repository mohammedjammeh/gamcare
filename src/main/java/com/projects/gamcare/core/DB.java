package com.projects.gamcare.core;

import java.io.FileInputStream;
import java.sql.*;
import java.util.*;

public class DB {
    private Connection dbConnection;
    private String sql;
    private String mainTable;

    private List<String> whereValues = new ArrayList<>();

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

    public DB select(List<String> columns) {
        StringBuilder selectStatement = new StringBuilder("SELECT ");

        for (String column: columns) {
            selectStatement
                .append(column)
                .append(finaliseSelectColumn(column, columns));
        }

        sql = selectStatement.toString();

        return this;
    }

    private String finaliseSelectColumn(String column, List<String> columns) {
        return isLastColumn(column, columns) ? " " : ", ";
    }

    private Boolean isLastColumn(String column, List<String> columns) {
        return columns.indexOf(column) == columns.size() - 1;
    }

    public DB from(String table) {
        sql += "FROM " + table + " ";
        mainTable = table;

        return this;
    }

    public DB with(String secondTable) {
        sql += "INNER JOIN " + secondTable + " ON " + mainTable +  ".id = " + secondTable + "." + mainTable + "_id ";

        return this;
    }

    public DB where(String column, String operator, String value) {
        sql += "WHERE " + column + " " + operator + " ? ";

        whereValues.add(value);

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

        for (int i = 0; i < whereValues.size(); i++) {
            statement.setString(i + 1, whereValues.get(i));
        }

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
