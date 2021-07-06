package ba.unsa.etf.rs;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Person implements Serializable {

    private int id;
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty surname = new SimpleStringProperty();
    private SimpleIntegerProperty numberPoints  = new SimpleIntegerProperty();
    private SimpleObjectProperty<LocalDate> date = new SimpleObjectProperty<>();
    private static final long serialVersionUID = 1L;

    public Person(int id, String name, String surname, Integer numberPoints, LocalDate date) {
        this.id = id;
        setName(name);
        setSurname(surname);
        setNumberPoints(numberPoints);
        setDate(date);
    }

    public Person(String name, String surname, Integer numberPoints, LocalDate date) {
        setName(name);
        setSurname(surname);
        setNumberPoints(numberPoints);
        setDate(date);
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public int getNumberPoints() {
        return numberPoints.get();
    }

    public SimpleIntegerProperty numberPointsProperty() {
        return numberPoints;
    }

    public void setNumberPoints(int numberPoints) {
        this.numberPoints.set(numberPoints);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public SimpleObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    @Override
    public String toString(){
        return name.get() + ", " + surname.get() + ", " + numberPoints.get() + ", " + date.get().format(dateFormat);
    }

    public  static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd. MM. yyyy");
}
