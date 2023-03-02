package com.example.demo;

import StudentInfo.DBMessages;
import StudentInfo.DBUtils;
import StudentInfo.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static com.example.demo.LoginController.changeScene;

public class AdminController {
    @FXML
    private HBox root;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableView<Student> tableView;

    @FXML
    private TableColumn<Student, Integer> fnum;

    @FXML
    private TableColumn<Student, Integer> egn;

    @FXML
    private TableColumn<Student, String> fname;

    @FXML
    private TableColumn<Student, String> lname;

    @FXML
    private TableColumn<Student, Integer> semester;

    @FXML
    private TableColumn<Student, String> faculty;

    @FXML
    private TableColumn<Student, String> date;

    @FXML
    private Button removeButton;

    @FXML
    private Button addButton;

    @FXML
    private TextField removeStudent;

    @FXML
    private Button showAllStudents;

    @FXML
    private Button logout;

    @FXML
    private Button searchStudent;

    @FXML
    private TextField searchByFNum;

    @FXML
    private Button editStudent;

    @FXML
    private TextField facultyText;

    @FXML
    private Button searchFaculty;

    @FXML
    private Label studentsCount;

    @FXML
    private Button checkMessages;

    private DBUtils dbUtils = new DBUtils();


    public void initialize() { //receiving data from MySQL and makes the visual representation on a table.
        ObservableList<Student> list = FXCollections.observableArrayList(dbUtils.outputAllStudents());

        showTableInformation();

        tableView.setItems(list);
    }

    public void remove(ActionEvent event) {
        String text = removeStudent.getText();
        int facnum = Integer.parseInt(text);

        ObservableList<Student> list = tableView.getItems();
        Student studentToRemove = null;
        for (Student student : list) {
            if (student.getFnum() == facnum) {
                studentToRemove = student;
                break;
            }
        }
        if (studentToRemove != null) {
            list.remove(studentToRemove);
        }

        dbUtils.removeFromDB(facnum);
    }

    public void changeToAddScene(ActionEvent event) throws IOException {
        changeScene(event, "adminAdd.fxml");
    }

    public void showAllStudents(ActionEvent event){
        dbUtils.close();
        /*
        Application faced some problems by refreshing the tableview, so by this quick connection
        reset, the application now receive fully updated information.
         */
        dbUtils.open();
        studentsCount();
        initialize();
    }

    public void searchStudent(ActionEvent event){
        Student student = dbUtils.search(Integer.parseInt(searchByFNum.getText()));

        if(student != null){
            ObservableList<Student> list = FXCollections.observableArrayList(student);

            showTableInformation();

            tableView.setItems(list);
        }
    }

    public void editStudentInformation(ActionEvent event) throws IOException {
        changeScene(event, "adminEdit.fxml");
    }

    public void showTableInformation(){
        fnum.setCellValueFactory(new PropertyValueFactory<Student, Integer>("fnum"));
        egn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("EGN"));

        fname.setCellValueFactory(new PropertyValueFactory<Student, String>("fname"));
        lname.setCellValueFactory(new PropertyValueFactory<Student, String>("lname"));

        semester.setCellValueFactory(new PropertyValueFactory<Student, Integer>("semester"));
        faculty.setCellValueFactory(new PropertyValueFactory<Student, String>("faculty"));
        date.setCellValueFactory(new PropertyValueFactory<Student, String>("date"));
    }

    public void searchFaculty(ActionEvent event){
        String faculty = facultyText.getText();
        List<Student> students = dbUtils.searchFaculty(faculty);

        ObservableList<Student> list = FXCollections.observableArrayList(students);

        showTableInformation();

        tableView.setItems(list);
    }

    public void studentsCount(){
        int i = dbUtils.studentsInDB();
        studentsCount.setText(String.valueOf(i));
    }

    public void checkStudentMessages(ActionEvent event) throws IOException {
        DBMessages dbMessages = new DBMessages();
        dbMessages.open();

        changeScene(event,"adminSendMessage.fxml");
    }
}
