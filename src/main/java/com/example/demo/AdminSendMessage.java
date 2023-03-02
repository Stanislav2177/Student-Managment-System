package com.example.demo;

import StudentInfo.DBMessages;
import StudentInfo.Message;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.example.demo.LoginController.changeScene;

public class AdminSendMessage {
    DBMessages dbMessages = new DBMessages();

    @FXML
    private TableView<Message> tableView;

    @FXML
    private TableColumn<Message, Integer> id;

    @FXML
    private TableColumn<Message, Integer> facultyNumber;

    @FXML
    private TableColumn<Message, String> message;

    @FXML
    private TableColumn<Message, String> sender;

    @FXML
    private TableColumn<Message, Date> createdAt;

    @FXML
    private TextArea messageTextArea;

    @FXML
    private TextField messageTextField;

    @FXML
    private Button backToAdmin;

    @FXML
    private TextField rowTextField;

    @FXML
    private Button clearAll;

    @FXML
    private Button clearButton;

    @FXML
    private Button refresh;

    private Long lastClickTime;

    public void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<Message, Integer>("id"));
        facultyNumber.setCellValueFactory(new PropertyValueFactory<Message, Integer>("facultyNumber"));
        sender.setCellValueFactory(new PropertyValueFactory<Message, String>("sender"));
        message.setCellValueFactory(new PropertyValueFactory<Message, String>("text"));
        createdAt.setCellValueFactory(new PropertyValueFactory<Message, Date>("date"));

        // update the messageTextArea when a new message is selected
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Message selectedMessage = tableView.getSelectionModel().getSelectedItem();
                messageTextArea.setText(selectedMessage.getText());
            }
        });
    }

    @FXML
    public void handlePrintMessage() {
        String message = messageTextField.getText();
        System.out.println("Message: " + message);
    }

    public void backToAdmin(ActionEvent event) throws IOException {
        changeScene(event, "admin.fxml");
        dbMessages.close();
    }

    public void clearRow(ActionEvent event){//delete a single row
        int id = Integer.parseInt(rowTextField.getText());
        dbMessages.clearRow(id);
    }

    public void showMessages(ActionEvent event) throws IOException {//Present the data from database
        dbMessages.open();
        initialize();
        List<Message> messageList = dbMessages.getAllMessages();
        ObservableList<Message> observableList = FXCollections.observableArrayList(messageList);
        tableView.getItems().setAll(observableList);
    }

    public void clearAll(ActionEvent event) {//delete all the messages in the database
        messageTextField.clear();
        rowTextField.clear();
        tableView.getItems().clear();
        dbMessages.clearAll();
    }
}


