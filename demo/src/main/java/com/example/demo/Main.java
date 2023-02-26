package com.example.demo;

import StudentInfo.DBMessages;
import StudentInfo.DBUtils;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    DBUtils dbUtils = new DBUtils();
    DBMessages dbMessages = new DBMessages();

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(root);

            stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon/iconForApp.png")));

            stage.setTitle("Student Management System");
            stage.setScene(scene);

            stage.show();

            stage.setOnCloseRequest(event -> logout(stage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logout(Stage stage){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("logout");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("Do you want to save before exiting?: ");

        if(alert.showAndWait().get() == ButtonType.OK){
            dbUtils.close();
            dbMessages.close();
            stage.close();
        }
    }


    public static void main(String[] args) {
        launch();
    }
}