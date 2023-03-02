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
        /*
        This code checks the credentials of users logging into the system,
        based on their faculty number and EGN. It creates a Map that associates
        each faculty number with a corresponding EGN, allowing for quick credential checking
        with O(1) complexity. If the credentials are valid, the user is directed to either the
         admin or student scene, depending on their user type.

        Note that EGN stands for "EGN Number", which is a unique personal identification number used in Bulgaria.
        */

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

            FXMLLoader loader = new FXMLLoader(getClass().getResource("user.fxml"));
            Parent root = loader.load();

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