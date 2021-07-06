package ba.unsa.etf.rs;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.time.LocalDate;

public class PersonController {

    @FXML
    private TableView tablePerson;
    @FXML
    private TableColumn colName, colNumberPoints, colDate;
    @FXML
    private PersonDAO model;
    Stage editPerson = new Stage();
    Stage aboutWindow = new Stage();
    @FXML
    private Label statusMsg;

    public PersonController(PersonDAO model) {
        this.model = model;
    }

    public PersonController() {
    }

    @FXML
    private void updateSelectedPerson() {
        Person p = (Person) tablePerson.getSelectionModel().getSelectedItem();
        model.setCurrentPerson(p);
    }

    @FXML
    public void initialize() {
        statusMsg.setText("Program started.");
        colName.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        colNumberPoints.setCellValueFactory(new PropertyValueFactory<Person, Integer>("numberPoints"));
        colDate.setCellValueFactory(new PropertyValueFactory<LocalDate, String>("date"));
        tablePerson.setItems(model.getPersons());
        tablePerson.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>() {
            @Override
            public void changed(ObservableValue<? extends Person> observableValue, Person oldPerson, Person newPerson) {
                if (oldPerson != null) {
                }
                if (oldPerson == null) {

                } else {
                    updateSelectedPerson();
                }
                tablePerson.refresh();
            }
        });
    }


    public void exit(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }

    public void addPerson(ActionEvent actionEvent) {
    }

    public void editPerson(ActionEvent actionEvent) {
    }

    public void deletePerson(ActionEvent actionEvent) {
    }

    public void openAbout(ActionEvent actionEvent) {
    }

    @FXML
    private void checkDoubleClick(MouseEvent mouseEvent) {
    }
}
