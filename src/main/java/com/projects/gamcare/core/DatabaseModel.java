package com.projects.gamcare.core;

import java.io.FileInputStream;
import java.sql.*;
import java.util.*;

public class DatabaseModel {
    private Connection dbConnection;
    private String sql;

    protected List<Object> whereValues = new ArrayList<>();
    public Map<String, Object> attributes = new HashMap<>();





    public DatabaseModel() {
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





    public DatabaseModel select(List<String> columns) {
        StringBuilder selectStatement = new StringBuilder("SELECT ");

        for (String column: columns) {
            selectStatement
                .append(column)
                .append(finaliseSelectColumn(column, columns));
        }

        sql = selectStatement.toString();
        sql += "FROM " + getTableName() + " ";

        return this;
    }

    private String finaliseSelectColumn(String column, List<String> columns) {
        return isLastColumn(column, columns) ? " " : ", ";
    }

    private Boolean isLastColumn(String column, List<String> columns) {
        return columns.indexOf(column) == columns.size() - 1;
    }

    public DatabaseModel with(String secondTable) {
        sql += "INNER JOIN " + secondTable + " ON " + getTableName() +  ".id = " + secondTable + "." + getTableName() + "_id ";

        return this;
    }

    public DatabaseModel where(String column, String operator, Object value) {
        prepareSql();

        sql += whereValues.isEmpty() ? "WHERE " : "AND ";
        sql += column + " " + operator + " ? ";

        whereValues.add(value);

        return this;
    }

    public DatabaseModel orderBy(String column) {
        prepareSql();

        sql += "ORDER BY " + column;

        return this;
    }






    public List<DatabaseModel> getAll() {
        List<DatabaseModel> results = new ArrayList<>();

        try {
            ResultSet queryResults = this.query();
            while (queryResults.next()) {
                DatabaseModel rowInstance = newRowInstance(queryResults);
                results.add(rowInstance);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return results;
    }

    private ResultSet query() throws SQLException {
        prepareSql();

        PreparedStatement statement = dbConnection.prepareStatement(sql);

        for (int i = 0; i < whereValues.size(); i++) {
            setStatementValue(statement, i+1, whereValues.get(i));
        }

        return statement.executeQuery();
    }

    private void prepareSql() {
        if(sql == null)
            sql = "SELECT * FROM " + getTableName() + " ";
    }


    private void setStatementValue(PreparedStatement statement, Integer index, Object value) throws SQLException {
        if(value instanceof Integer) {
            statement.setInt(index, (int) value);
        }

        if(value instanceof String) {
            statement.setString(index, (String) value);
        }
    }

    private DatabaseModel newRowInstance(ResultSet queryResults) throws SQLException {
        DatabaseModel instance = this.newInstanceOfThis();

        for (Map<String, String> columnData : getColumnsData(queryResults)) {
            putData(instance.attributes, columnData, queryResults);
        }

        return instance;
    }

    private DatabaseModel newInstanceOfThis() {
        DatabaseModel newInstance;

        try {
            newInstance = this.getClass().getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            newInstance = new DatabaseModel();
            e.printStackTrace();
        }

        return newInstance;
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





    public Object first() {
        List<DatabaseModel> resultsList = getAll();

        return resultsList.isEmpty()
            ? null
            : resultsList.get(0);
    }

    public Object last() {
        List<DatabaseModel> resultsList = getAll();
        int lastResultIndex = resultsList.size() - 1;

        return resultsList.isEmpty()
            ? null
            : resultsList.get(lastResultIndex);
    }

    public String getNameAttribute() {
        return (String) this.attributes.get("name");
    }

    public String getFullName() {
        return this.attributes.get("first_name") + " " + this.attributes.get("last_name");
    }





    public String getTableName() {
        return null;
    }
}
