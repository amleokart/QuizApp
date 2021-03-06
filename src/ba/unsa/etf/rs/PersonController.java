package ba.unsa.etf.rs;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class PersonController {

    @FXML
    private TableView tablePerson;
    @FXML
    private TableColumn colName, colNumberPoints, colDate;
    @FXML
    private PersonDAO model;
    Stage editPerson = new Stage();
    Stage aboutWindow = new Stage();
    private PersonDAO dao;
    private ObservableList<Person> listPersons;

    @FXML
    private Label statusMsg;

    public PersonController() {
        dao = PersonDAO.getInstance();
        listPersons = FXCollections.observableArrayList(dao.getPersons());
    }

    public PersonController(PersonDAO model) {
        this.model = model;
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
        tablePerson.setItems(listPersons);
        //tablePerson.setItems(model.getPersons());
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

    private void updateTableView() {
        int index = tablePerson.getSelectionModel().getSelectedIndex();
        tablePerson.getItems().clear();
        model.loadPersons();
        tablePerson.setItems(model.getPersons());
        tablePerson.requestFocus();
        tablePerson.getSelectionModel().select(index);
    }

    public void openNewWindow(Person person) {
        if(!editPerson.isShowing()) {
            boolean adding = false;
            if (person == null) {
                adding = true;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EditPerson.fxml"));
            EditPersonController editPersonController = new EditPersonController(person);
            loader.setController(editPersonController);
            Parent root = null;
            try {
                root = loader.load();
                if (person == null) {
                    editPerson.setTitle("Add new person");
                }
                editPerson.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                editPerson.setResizable(false);
                editPerson.show();

                boolean finalAdding = adding;
                editPerson.setOnHiding(Event -> {
                    Person newPerson = editPersonController.getPerson();
                    if (newPerson != null) {
                        if (finalAdding) {
                            model.addPerson(newPerson);
                            updateTableView();
                            statusMsg.setText("Person added.");
                            tablePerson.getSelectionModel().selectLast();
                        }
                    } else {
                        if (finalAdding) {
                            statusMsg.setText("Person not added.");
                        }
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addPerson(ActionEvent actionEvent) {
        statusMsg.setText("Adding new book.");
        if(!editPerson.isShowing()) {
            openNewWindow(null);
        }
    }

    public void deletePerson(ActionEvent actionEvent) {
        statusMsg.setText("Deleting person");
        if ( model.getCurrentPerson() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete selected person?", ButtonType.OK, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                model.deletePerson();
                updateTableView();
                tablePerson.getSelectionModel().selectFirst();
                statusMsg.setText("Person deleted.");
            } else {
                statusMsg.setText("Person not deleted.");
            }
        }
    }

    public void openAbout(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("You have the ability to add and delete a person. \nThe list that appears at the beginning is a list of people who exist in the database. \nAfter adding a new person, they should appear in the list and be saved in the database. \nThe possibility of editing existing persons is excluded due to the potential situation of revision of points.");
        alert.showAndWait();
    }

    @FXML
    private void checkDoubleClick(MouseEvent mouseEvent) {
    }
}
