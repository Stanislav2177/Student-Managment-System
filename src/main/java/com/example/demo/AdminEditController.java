package com.example.demo;

import StudentInfo.DBUtils;
import StudentInfo.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.demo.LoginController.changeScene;

public class AdminEditController implements Initializable {
    DBUtils dbUtils = new DBUtils();

    @FXML
    private ChoiceBox<String> myChoiceBox;

    @FXML
    private Button saveChanges;

    @FXML
    private TextField facultyNumber;

    @FXML
    private TextField newValue;

    @FXML
    private Button goBackToAdmin;

    @FXML
    private Button checkStudent;

    @FXML
    private Label firstAndLastName;

    @FXML
    private Label egnSemester;

    @FXML
    private Label facultyDateOfAdding;


    private String[] choices = {"EGN", "first_name", "last_name", "semester", "faculty", "date of adding"};


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myChoiceBox.getItems().addAll(choices);
    }

    public void editStudentInfo(ActionEvent event){
        /*
        Basic logic based on a choice from UI, relative to that choice
        it will affect to the changes which will be performed in the MySQL
        */
        String choice = myChoiceBox.getValue();
        String value = newValue.getText();
        int fnum = Integer.parseInt(facultyNumber.getText());

        if(choice.equals("EGN")){
            String paddedValue = String.format("%010d", Long.parseLong(value));
            dbUtils.editStudentInformation(fnum, choice, paddedValue);
        } else {
            dbUtils.editStudentInformation(fnum, choice, value);
        }
    }

    public void backToAdmin(ActionEvent event) throws IOException {
        changeScene(event, "admin.fxml");
    }

    public void checkStudent(ActionEvent event){//Show the student info in the application
        int fnum = 0;
        try{
            fnum = Integer.parseInt(facultyNumber.getText());

        }catch (NumberFormatException e){
            firstAndLastName.setText("add only numbers");
        }
        Student search = dbUtils.search(fnum);

        if(search != null){
            String flName = "First name: "+ search.getFname() + " " + "Last name:" + search.getLname();
            String egnSem = "EGN:"+ search.getEGN() + " semester:" + search.getSemester();
            String facultyDate = "Faculty:" + search.getFaculty() + " " + "Date: " + search.getDate();

            firstAndLastName.setText(flName);
            egnSemester.setText(egnSem);
            facultyDateOfAdding.setText(facultyDate);
        }else{
            firstAndLastName.setText("incorrect faculty number");
        }
    }
}
