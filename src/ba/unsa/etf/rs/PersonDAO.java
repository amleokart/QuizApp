package ba.unsa.etf.rs;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PersonDAO {

    private ObservableList<Person> persons = FXCollections.observableArrayList();
    private SimpleObjectProperty<Person> currentPerson  = null;
    private static PersonDAO instance = null;
    private Connection conn;
    private PreparedStatement getPersonStatement, addPersonStatement, deleteCurrentPersonStatement, deleteAllPersonsStatement, getMaxPersonId, loginStatement;

    public ObservableList<Person> getPersons() {
        return persons;
    }

    public void setPersons(ObservableList<Person> persons) {
        this.persons = persons;
    }

    public Person getCurrentPerson() {
        if (currentPerson == null) {
            return null;
        }
        return currentPerson.get();
    }

    public SimpleObjectProperty<Person> currentPersonProperty() {
        return currentPerson;
    }

    public void setCurrentPerson(Person currentPerson) {
        if (this.currentPerson == null) {
            this.currentPerson = new SimpleObjectProperty<>(currentPerson);
        } else {
            this.currentPerson.set(currentPerson);
        }
    }

    public static PersonDAO getInstance() {
        if (instance == null) {
            initialize();
        }
        return instance;
    }

    public static void deleteInstance() {
        if (instance != null) {
            try {
                instance.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        instance = null;
    }

    private static void initialize() {
        instance = new PersonDAO();
    }

    private void prepareStatements() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:person.db");
            Class.forName("org.sqlite.JDBC");
            addPersonStatement = conn.prepareStatement("INSERT INTO person (id, name, surname, number_points, date)" + " VALUES (?,?,?,?,?); COMMIT;");

            deleteCurrentPersonStatement = conn.prepareStatement("DELETE FROM person WHERE id=?; COMMIT;");

            deleteAllPersonsStatement = conn.prepareStatement("DELETE FROM person WHERE 1 = 1; COMMIT;");

            getPersonStatement = conn.prepareStatement("SELECT id, name, surname, number_points, date FROM person ORDER BY id ASC ;");

            getMaxPersonId = conn.prepareStatement("SELECT MAX(id) + 1 FROM person;");

            loginStatement = conn.prepareStatement("SELECT * FROM login WHERE username=? and password=?");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("No connection driver found");
            e.printStackTrace();
        }
    }

    public void loadPersons() {
        try {
            ResultSet set = getPersonStatement.executeQuery();
            int i = 0;
            while(set.next()) {
                Integer id = set.getInt(1);
                persons.add(new Person(id, set.getString(2), set.getString(3), set.getInt(4), set.getDate(5).toLocalDate()));
                i++;
            }
            if (persons.size() > 0) {
                currentPerson = new SimpleObjectProperty<>(persons.get(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PersonDAO() {
        prepareStatements();
        loadPersons();
    }


    public void addPerson(Person person) {
        try {
            ResultSet resultSet = getMaxPersonId.executeQuery();
            int id;
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            } else {
                id = 0;
            }
            addPersonStatement.setInt(1, id);
            addPersonStatement.setString(2, person.nameProperty().get());
            addPersonStatement.setString(3, person.surnameProperty().get());
            addPersonStatement.setInt(4, person.numberPointsProperty().get());
            addPersonStatement.setDate(5, Date.valueOf(person.dateProperty().get()));
            addPersonStatement.executeUpdate();
            person.setId(id);
            persons.add(person);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePerson() {
        try {
            if (currentPerson != null) {
                deleteCurrentPersonStatement.setInt(1, currentPerson.get().getId());
                deleteCurrentPersonStatement.executeUpdate();
                persons.remove(currentPerson.get());
                currentPerson = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearAll() {
        try {
            deleteAllPersonsStatement.executeUpdate();
            persons.clear();
            currentPerson = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void defaultData() {
        addPerson(new Person("Elma", "Trako", 7, LocalDate.now()));
    }

    public String getPersonList() {
        String list= "";
        for(int i=0; i< persons.size(); i++)
            list += persons.get(i).toString() + "\n";
        return list;
    }

    public boolean validate(String username, String password) throws SQLException {
        try {
            loginStatement.setString(1,username);
            loginStatement.setString(2,password);
            ResultSet resultSet = loginStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
        }
        return false;
    }

}
