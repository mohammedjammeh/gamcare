package com.projects.gamcare.core;

import java.io.FileInputStream;
import java.sql.*;
import java.util.*;

public class DB {
    private Connection dbConnection;
    private String sql;
    private String mainTable;

    protected List<String> whereValues = new ArrayList<>();





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
        sql += whereValues.isEmpty() ? "WHERE " : "AND ";
        sql += column + " " + operator + " ? ";

        whereValues.add(value);

        return this;
    }

    public DB orderBy(String column) {
        sql += "ORDER BY " + column;

        return this;
    }





    public List<Map<String, Object>> get() {
        List<Map<String, Object>> results = new ArrayList<>();

        try {
            ResultSet queryResults = this.query();
            while (queryResults.next()) {
                Map<String, Object> row = getRowValues(queryResults);
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

    private Map<String, Object> getRowValues(ResultSet queryResults) throws SQLException {
        Map<String, Object> row = new HashMap<>();

        for (Map<String, String> columnData : getColumnsData(queryResults)) {
            putData(row, columnData, queryResults);
        }

        return row;
    }

    private Map<String, Object> putData(Map<String, Object> row, Map<String, String> columnData, ResultSet queryResults) throws SQLException {
        String columnName = columnData.get("name");
        String columnType = columnData.get("type");

        if(Objects.equals(columnType, "INT")) {
            row.put(columnName, queryResults.getInt(columnName));
        }

        if(Objects.equals(columnType, "VARCHAR") || Objects.equals(columnType, "CHAR")) {
            row.put(columnName, queryResults.getString(columnName));
        }

        if(Objects.equals(columnType, "DATETIME")) {
            row.put(columnName, queryResults.getDate(columnName));
        }

        if(Objects.equals(columnType, "BLOB")) {
            row.put(columnName, queryResults.getBytes(columnName));
        }

        return row;
    }

    private Collection<Map<String, String>> getColumnsData(ResultSet queryResults) throws SQLException {
        Collection<Map<String, String>> columnsData = new ArrayList<>();
        ResultSetMetaData metaData = queryResults.getMetaData();

        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            Map<String, String> column = new HashMap<>();

            column.put("name", metaData.getColumnName(i));
            column.put("type", metaData.getColumnTypeName(i));

            columnsData.add(column);
        }

        return columnsData;
    }





    public Map<String, Object> first() {
        List<Map<String, Object>> resultsList = get().stream().toList();

        return resultsList.isEmpty()
            ? new HashMap<>()
            : resultsList.get(0);
    }
}
