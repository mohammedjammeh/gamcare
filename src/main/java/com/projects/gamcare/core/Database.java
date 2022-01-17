package com.projects.gamcare.core;

import com.projects.gamcare.models.main.Model;

import java.io.FileInputStream;
import java.sql.*;
import java.util.*;

public class Database {
    private Connection connection;
    private Model model;

    private String selectSql;
    private List<Object> selectColumns = new ArrayList<>();
    private List<Object> whereValues = new ArrayList<>();

    private String insertSql;

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
     * Select Query Builders
     */
    public Database select(List<Object> columns) {
        selectColumns = columns;

        StringBuilder selectStatement = new StringBuilder("SELECT ");

        for (Object column: columns) {
            selectStatement
                .append(column)
                .append(selectColumnEnd(column, columns));
        }

        selectSql = selectStatement.toString();
        selectSql += "FROM " + model.getTableName() + " ";

        return this;
    }

    public Database with(String anotherTableName) {
        prepareSelectQuery();

        selectSql += "INNER JOIN " + anotherTableName + " ON " + model.getTableName() +  ".id = " + anotherTableName + "." + model.getTableName() + "_id ";

        return this;
    }

    public Database withMany( String anotherTableName, String pivotTableName) {
        prepareSelectQuery();

        selectSql += "INNER JOIN " + pivotTableName + " ON " + model.getTableName() +  ".id = " + pivotTableName + "." + model.getTableName() + "_id ";
        selectSql += "INNER JOIN " + anotherTableName + " ON " + pivotTableName + "." + anotherTableName + "_id = " + anotherTableName + ".id ";

        return this;
    }

    public Database where(String column, String operator, Object value) {
        prepareSelectQuery();

        selectSql += whereValues.isEmpty() ? "WHERE " : "AND ";
        selectSql += column + " " + operator + " ? ";

        whereValues.add(value);

        return this;
    }

    public Database orderBy(String column) {
        prepareSelectQuery();

        selectSql += "ORDER BY " + column;

        return this;
    }

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
                bytesData = queryResults.getBytes(selectColumns.get(0).toString());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return bytesData;
    }

    private ResultSet query() throws SQLException {
        prepareSelectQuery();

        PreparedStatement statement = connection.prepareStatement(selectSql);

        for (int i = 0; i < whereValues.size(); i++) {
            setStatementValue(statement, i+1, whereValues.get(i));
        }

        return statement.executeQuery();
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

    private void putData(Map<String, Object> row, Map<String, String> columnData, ResultSet queryResults) throws SQLException {
        String columnName = columnData.get("name");
        String columnType = columnData.get("type");

        if(Objects.equals(columnType, "INT")) {
            Integer columnValue = queryResults.getInt(columnName) == 0 ? null : queryResults.getInt(columnName);
            row.put(columnName, columnValue);
            return;
        }

        if(Objects.equals(columnType, "DATETIME")) {
            row.put(columnName, queryResults.getDate(columnName));
            return;
        }

        if(Objects.equals(columnType, "BLOB")) {
            row.put(columnName, queryResults.getBytes(columnName));
            return;
        }

        row.put(columnName, queryResults.getString(columnName));
    }

    public void prepareSelectQuery() {
        if (selectSql == null) {
            select(List.of("*"));
        }
    }


    /**
     * Insert Query Builders
     */
    public Database fields(List<Object> columns) {
        StringBuilder insertStatement = new StringBuilder("INSERT INTO " + model.getTableName() + " (");

        for (Object column : columns) {
            insertStatement
                .append(column)
                .append(insertColumnEnd(column, columns));
        }

        insertSql = insertStatement + ") ";

        return this;
    }

    public Database values(List<Object> values) {
        StringBuilder insertStatement = new StringBuilder(insertSql + "values (");

        for (Object value : values) {
            insertStatement
                .append(insertValue(value))
                .append(insertColumnEnd(value, values));
        }

        insertSql = insertStatement + ")";

        return this;
    }

    public void insert() {
        try {
            PreparedStatement statement = connection.prepareStatement(insertSql);
            statement.execute(insertSql);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private Object insertValue(Object value) {
        if (value instanceof String) {
            return "'" + value + "'";
        }

        return value;
    }


    /**
     * General Methods
     */
    private String selectColumnEnd(Object column, List<Object> columns) {
        return isLastColumn(column, columns) ? " " : ", ";
    }

    private String insertColumnEnd(Object column, List<Object> columns) {
        return isLastColumn(column, columns) ? "" : ", ";
    }

    private Boolean isLastColumn(Object column, List<Object> columns) {
        return columns.indexOf(column) == columns.size() - 1;
    }
}
