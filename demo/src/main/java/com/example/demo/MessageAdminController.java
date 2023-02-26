package com.example.demo;

import StudentInfo.DBMessages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class MessageAdminController{
    DBMessages dbMessages = new DBMessages();

    public MessageAdminController(){

    }

    @FXML
    private Button sendMessage;

    @FXML
    private TextArea textArea;

    public void sendMessage(ActionEvent event){
        String text = textArea.getText();

        System.out.println(text);

        UserController userController = new UserController();
        int facultyNumber = userController.getFacultyNumber();
        String nameOfStudent = userController.getNameOfStudent();

        System.out.println(facultyNumber);
        System.out.println(nameOfStudent);

        message(facultyNumber, nameOfStudent, text);
    }

    private void message(int fnum, String sender, String text) {
        dbMessages.addMessage(fnum, sender, text);
    }


}
