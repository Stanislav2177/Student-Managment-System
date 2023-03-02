package StudentInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBMessages {
    private static Connection connection;
    private static PreparedStatement statement;
    private static List<Message> allMessages = new ArrayList<>();

    public DBMessages(){
    }

    public static void main(String[] args) {
        DBMessages dbMessages = new DBMessages();
        dbMessages.open();

        //testing locally the methods

        dbMessages.close();
    }

    public boolean open(){
        /*
          Opens a connection to the MySQL database and retrieves all messages from the "messages" table.
          Stores all messages in a List of Message objects.

          return true if the database connection is successful and messages are retrieved, false otherwise
         */
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_info", "root", "1234");
            statement = connection.prepareStatement("SELECT * FROM messages");

            System.out.println("Database is opened");

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Message message = new Message();
                message.setId(resultSet.getInt(1));
                message.setFacultyNumber(resultSet.getInt(2));
                message.setSender(resultSet.getString(3));
                message.setText(resultSet.getString(4));
                message.setDate(resultSet.getDate(5));

                allMessages.add(message);
            }

            System.out.println("successfully loaded data from MySQL");
            return true;
        } catch (SQLException e) {
            System.out.println("Problem in DBMessages and more especially in open() = " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public  void close() {
        try {
            if(statement != null) {
                statement.close();
            }

            if(connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close the connection = " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Database is closed");
    }


    public static List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT * FROM messages");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Message message = new Message();
                message.setId(resultSet.getInt(1));
                message.setFacultyNumber(resultSet.getInt(2));
                message.setSender(resultSet.getString(3));
                message.setText(resultSet.getString(4));
                message.setDate(resultSet.getDate(5));
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }




    public void addMessage(int facultyNumber, String sender, String message){
        String sql = "INSERT INTO messages (faculty_number, sender, message) VALUES (?, ?, ?)";
        try{
            statement = connection.prepareStatement(sql);
            statement.setInt(1,facultyNumber);
            statement.setString(2,sender);
            statement.setString(3,message);

            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void clearRow(int id) {
        String sql = "DELETE FROM messages WHERE id = ?";

        try{
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void clearAll() {
        String sql = "DELETE FROM messages";

        try {
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
