package StudentInfo;

public class Student {
    private int fnum;
    private int EGN;
    private String fname;
    private String lname;
    private int semester;
    private String faculty;
    private String date;

    public Student(int fnum, int EGN, String fName, String lName, int semester, String faculty, String date) {
        this.fnum = fnum;
        this.EGN = EGN;
        this.fname = fName;
        this.lname = lName;
        this.semester = semester;
        this.faculty = faculty;
        this.date = date;
    }

    public Student(){

    }

    public int getFnum() {
        return fnum;
    }

    public void setFnum(int fnum) {
        this.fnum = fnum;
    }

    public int getEGN() {
        return EGN;
    }

    public void setEGN(int EGN) {
        this.EGN = EGN;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Student{" +
                "fnum=" + fnum +
                ", EGN=" + EGN +
                ", fName='" + fname + '\'' +
                ", lName='" + lname + '\'' +
                ", semester=" + semester +
                ", faculty='" + faculty + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
