package com.projects.gamcare.core;

import com.projects.gamcare.models.main.Model;

import java.io.FileInputStream;
import java.sql.*;
import java.util.*;

public class Database {
    private Connection connection;
    private String sql;
    private Model model;

    protected List<String> selectColumns = new ArrayList<>();
    protected List<Object> whereValues = new ArrayList<>();

    public Database(Model model) {
        try {
            Properties properties = new Properties();
            FileInputStream propertiesFile = new FileInputStream("src/main/java/com/projects/gamcare/config/config.properties");
            properties.load(propertiesFile);

            connection = DriverManager.getConnection(
                properties.getProperty("database_url"),
                properties.getProperty("database_user"),
                properties.getProperty("database_password")
            );

            this.model = model;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    /**
     * Query Builders
     */
    public Database select(List<String> columns) {
        selectColumns = columns;

        StringBuilder selectStatement = new StringBuilder("SELECT ");

        for (String column: columns) {
            selectStatement
                .append(column)
                .append(endOf(column));
        }

        sql = selectStatement.toString();
        sql += "FROM " + model.getTableName() + " ";

        return this;
    }

    private String endOf(String column) {
        return isLastColumn(column, selectColumns) ? " " : ", ";
    }

    private Boolean isLastColumn(String column, List<String> columns) {
        return columns.indexOf(column) == columns.size() - 1;
    }

    public Database with(String anotherTableName) {
        prepareSql();

        sql += "INNER JOIN " + anotherTableName + " ON " + model.getTableName() +  ".id = " + anotherTableName + "." + model.getTableName() + "_id ";

        return this;
    }

    public Database where(String column, String operator, Object value) {
        prepareSql();

        sql += whereValues.isEmpty() ? "WHERE " : "AND ";
        sql += column + " " + operator + " ? ";

        whereValues.add(value);

        return this;
    }

    public Database orderBy(String column) {
        prepareSql();

        sql += "ORDER BY " + column;

        return this;
    }


    /**
     * Query Methods
     */
    public List<Model> getAll() {
        List<Model> results = new ArrayList<>();

        try {
            ResultSet queryResults = this.query();
            while (queryResults.next()) {
                Model rowInstance = newModelInstance(queryResults);
                results.add(rowInstance);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return results;
    }

    public byte[] getBytes() {
        byte[] bytesData = new byte[0];

        try {
            ResultSet queryResults = this.query();
            while (queryResults.next()) {
                bytesData = queryResults.getBytes(selectColumns.get(0));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return bytesData;
    }

    private ResultSet query() throws SQLException {
        prepareSql();

        PreparedStatement statement = connection.prepareStatement(sql);

        for (int i = 0; i < whereValues.size(); i++) {
            setStatementValue(statement, i+1, whereValues.get(i));
        }

        return statement.executeQuery();
    }

    private void prepareSql() {
        if(sql == null)
            sql = "SELECT * FROM " + model.getTableName() + " ";
    }


    private void setStatementValue(PreparedStatement statement, Integer index, Object value) throws SQLException {
        if(value instanceof Integer) {
            statement.setInt(index, (int) value);
        }

        if(value instanceof String) {
            statement.setString(index, (String) value);
        }
    }

    private Model newModelInstance(ResultSet queryResults) throws SQLException {
        Model modelInstance = this.createModelInstance();

        for (Map<String, String> columnData : getColumnsData(queryResults)) {
            putData(modelInstance.attributes, columnData, queryResults);
        }

        return modelInstance;
    }

    private Model createModelInstance() {
        Model modelInstance;

        try {
            modelInstance = model.getClass().getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            modelInstance = new Model();
            e.printStackTrace();
        }

        return modelInstance;
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
            Integer columnValue = queryResults.getInt(columnName) == 0 ? null : queryResults.getInt(columnName);
            row.put(columnName, columnValue);
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


    /**
     * Results Methods
     */
    public Object first() {
        List<Model> resultsList = getAll();

        return resultsList.isEmpty()
            ? null
            : resultsList.get(0);
    }

    public Object last() {
        List<Model> resultsList = getAll();
        int lastResultIndex = resultsList.size() - 1;

        return resultsList.isEmpty()
            ? null
            : resultsList.get(lastResultIndex);
    }
}
