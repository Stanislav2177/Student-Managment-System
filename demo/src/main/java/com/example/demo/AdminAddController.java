package com.example.demo;

import StudentInfo.DBUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

public class AdminAddController {
    DBUtils dbUtils = new DBUtils();

    @FXML
    private TextField fnum;

    @FXML
    private TextField EGN;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private TextField faculty;

    @FXML
    private TextField semester;

    @FXML
    private TextField date;

    @FXML
    private Button saveButton;

    @FXML
    private Button backToAdmin;

    public void addIntoDB(ActionEvent event){

        add();
    }

    public void add(){
        dbUtils.open();

        int facnum = Integer.parseInt(fnum.getText());
        int egn = Integer.parseInt(EGN.getText());

        String firstName = fname.getText();
        String lastName = lname.getText();

        int semesterNo = Integer.parseInt(semester.getText());
        String fac = faculty.getText();
        String dateOfAdding = date.getText();
        DBUtils.addIntoDB(facnum, egn, firstName, lastName, semesterNo, fac, dateOfAdding);
    }

    public void backToAdmin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin.fxml"));
        Parent root = loader.load();

//        AdminController adminController = loader.getController();
//        adminController.initialize(DBUtils.getStudentList());

        Scene scene = new Scene(root);
        Stage currentStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        currentStage.setScene(scene);
    }

}
