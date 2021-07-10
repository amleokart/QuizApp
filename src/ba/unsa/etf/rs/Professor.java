package ba.unsa.etf.rs;

import java.time.LocalDate;

public class Professor extends Person {

    private String professorUsername;

    public Professor() {
        super();
    }

    public Professor(int id, String name, String surname, Integer numberPoints, LocalDate date, String professorUsername) {
        super(id, name, surname, numberPoints, date);
        this.professorUsername = professorUsername;
    }

    public String getProfessorUsername() {
        return professorUsername;
    }

    public void setProfessorUsername(String professorUsername) {
        this.professorUsername = professorUsername;
    }
}
