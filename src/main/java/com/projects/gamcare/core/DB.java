package com.projects.gamcare.core;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

public class DB {
    private Boolean error;
    private ResultSet results;


    public DB(String sql) {
        try {
            Properties properties = new Properties();
            FileInputStream ip = new FileInputStream("src/main/java/com/projects/gamcare/config/config.properties");
            properties.load(ip);

            Connection dbConnection = DriverManager.getConnection(
                properties.getProperty("database_url"),
                properties.getProperty("database_user"),
                properties.getProperty("database_password")
            );

            PreparedStatement statement = dbConnection.prepareStatement(sql);
            results = statement.executeQuery();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public Boolean getError() {
        return error;
    }

    public void getResults() {
        System.out.println(this.getColumnNames(results));
    }

    private Collection<String> getColumnNames(ResultSet results) {
        Collection<String> columnNames = new ArrayList<>();

        try {
            ResultSetMetaData metaData = results.getMetaData();

            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                columnNames.add(metaData.getColumnName(i));
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

        return columnNames;
    }
}
