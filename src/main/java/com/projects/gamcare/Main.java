package com.projects.gamcare;

import com.projects.gamcare.core.Hash;
import com.projects.gamcare.core.SceneTool;
import com.projects.gamcare.core.TimeTool;
import com.projects.gamcare.enums.DoctorLevel;
import com.projects.gamcare.models.Hospital;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamcare", "root", "3aj3!96wMWeyU9&z");

//          SELECT
//            String selectSql = "SELECT * FROM users WHERE email_address = ?";
//            PreparedStatement statement = myConn.prepareStatement(selectSql);
//            statement.setString(1, "mohammedjammeh@yahoo.com");
//
//            ResultSet results = statement.executeQuery();
//
//            while (results.next()) {
//                byte[] userSalt = results.getBytes("salt");
//                String userHash = results.getString("hash");
//
//                String inputHash = Hash.generate("password123", userSalt);
//                System.out.println(inputHash);
//            }






//            INSERT
//            String insertSql = "INSERT INTO users (first_name, middle_name, last_name, email_address, phone_number, relevant_link, date_of_birth, place_of_birth, compound, town, " +
//                "type, patients_id, doctors_id, created_at, updated_at, titles_id, genders_id, tribes_id, regions_id, other_details, hash) " +
//                "values ('Manjiki', 'Mbinki', 'Jammeh', 'manjikijamme@yahoo.com', '07506259330', 'https://www.google.co.uk/', '2022-01-20', 'Tallinding', 'Jammeh Kunda', 'Bali', " +
//                "'PATIENT', 3, null, '2022-01-20', '2022-01-20', 1, 1, 1, 1, null, '72F0E29ED313066DD1C8172801E70A1C705C6FAE35885EFA94D8B8FA7C7B372C')";

//            String insertSql = "insert into users"
//                + "(first_name, middle_name, last_name, email_address, phone_number, relevant_link, date_of_birth, place_of_birth, compound, town, type, patients_id, doctors_id, "
//                + "created_at, updated_at, titles_id, genders_id, tribes_id, regions_id, other_details, salt, hash) "
//                + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//            byte[] salt = Hash.createSalt();
//            String hash = Hash.generate("password123", salt);
//
//            PreparedStatement statement = myConn.prepareStatement(insertSql);
//            statement.setString(1, "musa");
//            statement.setString(2, "small");
//            statement.setString(3, "salty");
//            statement.setString(4, "bailo@gmail.com");
//            statement.setString(5, "07506259330");
//            statement.setString(6, "https://bailo.com/");
//            statement.setDate(7, new Date(22, 1, 20));
//            statement.setString(8, "brikama");
//            statement.setString(9, "Jallow kunda");
//            statement.setString(10, "Brikama");
//            statement.setString(11, "PATIENT");
//            statement.setInt(12, 1);
//            statement.setString(13, null);
//            statement.setDate(14, new Date(22, 1, 20));
//            statement.setDate(15, new Date(22, 1, 20));
//            statement.setInt(16, 1);
//            statement.setInt(17, 1);
//            statement.setInt(18, 1);
//            statement.setInt(19, 1);
//            statement.setString(20, "");
//            statement.setBytes(21, salt);
//            statement.setString(22, hash);
//
//            statement.execute();





//            UPDATE
//            String updateSql = "update users set salt = ?, hash = ? where id = ?";
//            PreparedStatement statement = myConn.prepareStatement(updateSql);
//
//            byte[] salt = Hash.createSalt();
//            String hash = Hash.generate("password123", salt);
//
//            statement.setBytes(1, salt);
//            statement.setString(2, hash);
//            statement.setInt(3, 4);
//
//            statement.executeUpdate();




//            DELETE
//            String deleteSql = "delete from users where email='bailo@gmail.com'";
//
//            int rowsAffected = myStmt.executeUpdate(deleteSql);
//
//            System.out.println("Number of rows affected: " + rowsAffected);

        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }



//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/login.fxml"));

//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/hospital/index.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/hospital/create.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/hospital/show.fxml"));

//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/doctor/index.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/doctor/create.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/doctor/show.fxml"));

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/patient/create.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/patient/show.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1000, 1000);
        stage.setTitle("Gamcare");
        stage.setScene(scene);
        stage.show();

//        SceneTool.switchToLogin();
    }

    public static void main(String[] args) {
        launch();
    }
}