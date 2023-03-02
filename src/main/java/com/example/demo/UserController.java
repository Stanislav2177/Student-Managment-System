package com.example.demo;

import StudentInfo.DBMessages;
import StudentInfo.DBUtils;
import StudentInfo.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.demo.LoginController.changeScene;


public class UserController {
    DBMessages dbMessages = new DBMessages();

    public static int facultyNumber;
    public static String nameOfStudent;

    @FXML
    private Label fNamePlusLname;

    @FXML
    private Label facultynum;

    @FXML
    private Label EGN;

    @FXML
    private Label faculty;

    @FXML
    private Label semester;

    @FXML
    private Label date;

    @FXML
    private Button openContactScene;

    @FXML
    private Button backToUser;

    @FXML
    private Button backToLogin;

    @FXML
    public void initialize(int fnum) {
        displayStudentInformation(fnum);
    }

    public UserController(){

    }

    public void displayStudentInformation(int fnum){
        /*
          Get the faculty number from the login controller,
          and based on that, receive all student information
          from Mysql and present it in the application.
         */
            DBUtils students = new DBUtils();
            Student student = students.getStudent(fnum);
            String fNameLname = student.getFname() + " " + student.getLname();

            fNamePlusLname.setText(fNameLname);
            facultynum.setText(String.valueOf(student.getFnum()));
            EGN.setText(String.valueOf(student.getEGN()));
            faculty.setText(student.getFaculty());
            semester.setText(String.valueOf(student.getSemester()));
            date.setText(student.getDate());

            nameOfStudent = student.getFname();
            facultyNumber = fnum;

            setFacultyNumber(fnum);
            setNameOfStudent(student.getFname());
    }

    public void openContactScene(ActionEvent event) throws IOException {
        changeScene(event, "contactAdmin.fxml");

    }

    public int getFacultyNumber() {
        return facultyNumber;
    }

    public void setFacultyNumber(int facultyNumber) {
        this.facultyNumber = facultyNumber;
    }

    public String getNameOfStudent() {
        return nameOfStudent;
    }

    public void setNameOfStudent(String nameOfStudent) {
        this.nameOfStudent = nameOfStudent;
    }

    @FXML
    private Button sendMessage;

    @FXML
    private TextArea textArea;

    public void sendMessage(ActionEvent event) {
        dbMessages.open();
        String text = textArea.getText();

        System.out.println(text);

        dbMessages.addMessage(facultyNumber, nameOfStudent, text); //using static variables
        // to receive faculty number and name of student from User controller;

        dbMessages.close();
    }

    public void backToUser(ActionEvent event) throws IOException {
        changeScene(event,"user.fxml");
    }

    public void backToLogin(ActionEvent event) throws IOException{
        changeScene(event,"login.fxml");
    }



}
