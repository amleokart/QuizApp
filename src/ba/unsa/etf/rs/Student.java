package ba.unsa.etf.rs;

import java.time.LocalDate;

public class Student extends Person {

    private String studentUsername;

    public Student() {
        super();
    }

    public Student(int id, String name, String surname, Integer numberPoints, LocalDate date, String studentUsername) {
        super(id, name, surname, numberPoints, date);
        this.studentUsername = studentUsername;
    }

    public String getStudentUsername() {
        return studentUsername;
    }

    public void setStudentUsername(String studentUsername) {
        this.studentUsername = studentUsername;
    }
}
