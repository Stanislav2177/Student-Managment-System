package StudentInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {

    private static Connection connection;
    private static PreparedStatement statement;
    private static Map<Integer, Integer> identityMap = new HashMap<>();
    private static Map<Integer, Student> allStudents = new HashMap<>();
    private static List<Student> studentList = new ArrayList<>();

    public DBUtils() {

    }

    public static void main(String[] args) {
        DBUtils loadStudents = new DBUtils();
        loadStudents.open();

        System.out.println(allStudents.size());

        loadStudents.close();
        System.out.println("Hello, world!");
    }


    public boolean open(){
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_info", "root", "1234");
            statement = connection.prepareStatement("SELECT * FROM students");

            System.out.println("Database is opened");

            ResultSet resultSet = statement.executeQuery();

            Student student;

            while(resultSet.next()){
                int fnum = resultSet.getInt(1);
                int EGN = resultSet.getInt(2);
                String fName = resultSet.getString(3);
                String lName = resultSet.getString(4);
                int semester = resultSet.getInt(5);
                String faculty = resultSet.getString(6);
                String date = String.valueOf(resultSet.getDate(7));

                identityMap.put(fnum, EGN);
                student = new Student(fnum, EGN, fName, lName, semester, faculty, date);
                allStudents.put(fnum, student);
                studentList.add(student);
            }
            System.out.println("successfully loaded data from MySQL");
            return true;
        } catch (SQLException e) {
            System.out.println("Problem in LoadStudents and more especially in open() = " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public void close() {
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

    public void removeFromDB(int fnum){
        try{
            statement.execute("DELETE FROM students WHERE faculty_number = " + fnum );
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void addIntoDB(int facultyNumber, int EGN, String firstName, String lastName, int semester, String faculty, String date){
        try{
//            statement.execute("INSERT INTO students VALUES " + facultyNumber + ", "
//            + EGN + ", " + firstName + ", " + lastName + ", " + semester + ", " + faculty + ", " + date);
            String sql = "INSERT INTO students (faculty_number, EGN, first_name, last_name, semester, faculty, date) VALUES (?, ?, ?, ?, ?, ?, ?)";

            statement = connection.prepareStatement(sql);

            statement.setInt(1, facultyNumber);
            statement.setInt(2, EGN);

            statement.setString(3, firstName);
            statement.setString(4, lastName);

            statement.setInt(5, semester);
            statement.setString(6, faculty);
            statement.setDate(7, Date.valueOf(date));

            statement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e){
            System.out.println("already added student with that faculty number " + e.getMessage());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Student search(int fnum){

        String sql = "SELECT * FROM students WHERE faculty_number = " + fnum;

        try {
            statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Student student = new Student();
                student.setFnum(resultSet.getInt(1));
                student.setEGN(resultSet.getInt(2));
                student.setFname(resultSet.getString(3));
                student.setLname(resultSet.getString(4));
                student.setSemester(resultSet.getInt(5));
                student.setFaculty(resultSet.getString(6));
                student.setDate(String.valueOf(resultSet.getDate(7)));
                return student;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Student editStudentInformation(int facultyNumber, String columnName, String newValue){
        String sql = "UPDATE students SET " + columnName + " = '" + newValue + "' WHERE faculty_number = " + facultyNumber;

        try{
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public List<Student> searchFaculty(String faculty){
        String sql = "SELECT * FROM students WHERE faculty = ?";
        List<Student> students = new ArrayList<>();
        try{
            statement = connection.prepareStatement(sql);

            statement.setString(1, faculty);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Student student = new Student();
                student.setFnum(resultSet.getInt(1));
                student.setEGN(resultSet.getInt(2));
                student.setFname(resultSet.getString(3));
                student.setLname(resultSet.getString(4));
                student.setSemester(resultSet.getInt(5));
                student.setFaculty(resultSet.getString(6));
                student.setDate(String.valueOf(resultSet.getDate(7)));

                students.add(student);
            }

            return students;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public int studentsInDB(){
        return allStudents.size();
    }



    public List<Student> outputAllStudents(){
        return new ArrayList<>(allStudents.values());
    }

    public Student getStudent(int fnum){
        return allStudents.get(fnum);
    }

    public boolean checkLogin(int fnum, int EGN){
        return identityMap.containsKey(fnum) && identityMap.containsValue(EGN);
    }

    public static List<Student> getStudentList() {
        return studentList;
    }
}
