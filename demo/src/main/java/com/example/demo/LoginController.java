package com.example.demo;

import StudentInfo.DBUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;

public class LoginController{
    DBUtils students = new DBUtils();
    UserController userController;

    @FXML
    private TextField fnum;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField egn;

    @FXML
    private Button changeToInfo;

    @FXML
    private Button backToLogin;

    @FXML
    private Button contacts;



    @FXML
    private Label incorrectCredentials;

    public void login(ActionEvent event) throws IOException {
        students.open();
        boolean adminResult = egn.getText().equals("admin") && fnum.getText().equals("admin");

        if(adminResult){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin.fxml"));
            Parent root = loader.load();

            AdminController adminController = loader.getController();
            adminController.studentsCount();
//            adminController.initialize(DBUtils.getStudentList());

            Scene scene = new Scene(root);
            Stage currentStage = (Stage)((Node)event.getSource()).getScene().getWindow();

            currentStage.setScene(scene);
            return;
        }

        int facnum = Integer.parseInt(fnum.getText());
        int EGN = Integer.parseInt(egn.getText());



        boolean result = students.checkLogin(facnum, EGN);
        System.out.println(adminResult + " is it admin ?");

        if(result){

            // Load the user.fxml file and set it as the scene of the current stage
            FXMLLoader loader = new FXMLLoader(getClass().getResource("user.fxml"));
            Parent root = loader.load();

            // Get the controller of the new scene and initialize it with the faculty number
            UserController userController = loader.getController();
            userController.initialize(facnum);

            Scene scene = new Scene(root);
            Stage currentStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            currentStage.setScene(scene);
        }

        incorrectCredentials.setText("Incorrect Credentials!");
    }

    public void changeToInfo(ActionEvent event) throws IOException {
        changeScene(event, "infoAboutProject.fxml");
    }

    public void backToLogin(ActionEvent event) throws IOException {
        changeScene(event, "login.fxml");
    }

    public void switchToContacts(ActionEvent event) throws IOException {
        changeScene(event,"contacts.fxml");
    }

    public static void changeScene(ActionEvent event,String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(LoginController.class.getResource(fxml));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage currentStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        currentStage.setScene(scene);

    }

}