package com.projects.gamcare;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        try {
//            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamcare", "root", "3aj3!96wMWeyU9&z");

//            // PREPARE STATMENT
//            String selectSql = "SELECT * FROM users WHERE email = ?";
//            PreparedStatement myStmt = myConn.prepareStatement(selectSql);
//            myStmt.setString(1, "mohammedjammeh@yahoo.com");
//            myStmt.executeQuery();
//
//            ResultSet myRs = myStmt.executeQuery("SELECT * FROM users");
//
//            while (myRs.next()) {
//                System.out.println(myRs.getString("first_name"));
//            }






//            Statement myStmt = myConn.createStatement();


//            SELECT
//            ResultSet myRs = myStmt.executeQuery("SELECT * FROM users");
//
//            while (myRs.next()) {
//                System.out.println(myRs.getString("first_name"));
//            }




//            INSERT
//            String insertSql = "insert into users"
//                    + "(title, first_name, middle_name, last_name, email, phone_number, relevant_link, date_of_birth, gender, compound, town, region, other_details)"
//                    + "values(1, 'Omar', 'Bailo', 'Jallow', 'bailo@gmail.com', '07506259330', 'https://bailo.com/', '1986-08-21', 1, 'Jallow kunda', 'Brikama', 1, 'I am chilling')";
//
//            myStmt.executeUpdate(insertSql);




//            UPDATE
//            String updateSql = "update users set middle_name='Musa' where id=2";
//
//            myStmt.executeUpdate(updateSql);




//            DELETE
//            String deleteSql = "delete from users where email='bailo@gmail.com'";
//
//            int rowsAffected = myStmt.executeUpdate(deleteSql);
//
//            System.out.println("Number of rows affected: " + rowsAffected);

//        }
//        catch (Exception exception)
//        {
//            exception.printStackTrace();
//        }


//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/login.fxml"));

//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/hospital/index.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/hospital/create.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/hospital/show.fxml"));

//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/doctor/index.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/doctor/create.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/doctor/show.fxml"));

//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/patient/create.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/patient/show.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1000, 1500);
        stage.setTitle("Gamcare");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}