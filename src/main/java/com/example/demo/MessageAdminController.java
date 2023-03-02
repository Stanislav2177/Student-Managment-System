package com.example.demo;

import StudentInfo.DBMessages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;


public class MessageAdminController{
    DBMessages dbMessages = new DBMessages();

    public MessageAdminController(){

    }

    @FXML
    private Button sendMessage;

    @FXML
    private TextArea textArea;

    public void sendMessage(ActionEvent event){
        /*
           Get the message which is provided by the student,
           store the text, faculty number and name of the student,
           for easier recognition when it's open from the admin.
         */

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
