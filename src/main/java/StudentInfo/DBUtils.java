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
        /*
        This method opens a connection to the MySQL database and retrieves
        all the student information from the "students" table. The retrieved
        data is then stored in an Identity Map and a HashMap. The Identity Map
        is used to ensure that only one instance of each Student object is created
        during the application's runtime. The HashMap is used to allow easy access to
        the Student objects by their faculty number. The retrieved Student objects are
        also added to a List. If there is a problem with opening the connection or retrieving
        the data, an error message is printed and the method returns false. Otherwise, the method returns true.
         */
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
        //Search from database and if it's all good, return student.
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
        /*
        By chosen column and new value, the student which refer to selected faculty number,
        the information in database will be changed
        */
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
      // Return a list of all students which are in the selected faculty.
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
