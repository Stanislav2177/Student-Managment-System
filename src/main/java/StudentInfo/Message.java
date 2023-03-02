package StudentInfo;

import java.util.Date;

public class Message {
    private int id;
    private int facultyNumber;
    private String sender;
    private String text;
    private Date date;

    public Message(int id, int facultyNumber, String sender, String text, Date date) {
        this.id = id;
        this.facultyNumber = facultyNumber;
        this.sender = sender;
        this.text = text;
        this.date = date;
    }
    public Message(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFacultyNumber() {
        return facultyNumber;
    }

    public void setFacultyNumber(int facultyNumber) {
        this.facultyNumber = facultyNumber;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", facultyNumber=" + facultyNumber +
                ", sender='" + sender + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
