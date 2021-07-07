package ba.unsa.etf.rs;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import java.time.LocalDate;

public class EditPersonController {

    @FXML
    private Button btnCancel, btnOk;
    @FXML
    private DatePicker dpDate;
    @FXML
    private TextField fldName, fldSurname, lastFocusedFld;
    @FXML
    private Spinner spinNumberPoints;
    private Person person = null;
    private boolean nameCorrectInput = false, dateCorrectInput = true, surnameCorrectInput = false, validated = false;

    public EditPersonController(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        if (validated) {
            return person;
        } else {
            return null;
        }
    }

    @FXML
    public void initialize() {
        if (person == null) {
            fldName.setText("");
            fldName.getStyleClass().add("fieldIncorrect");
            fldSurname.setText("");
            fldSurname.getStyleClass().add("fieldIncorrect");
            dpDate.setValue(LocalDate.now());
            dpDate.getEditor().getStyleClass().add("fieldCorrect");
            SpinnerValueFactory<Integer> spinPageCountValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 7, 0, 1);
            this.spinNumberPoints.setValueFactory(spinPageCountValueFactory);
        } else {
            fldName.setText(person.getName());
            fldName.getStyleClass().add("fieldCorrect");
            nameCorrectInput = true;

            fldSurname.setText(person.getSurname());
            fldSurname.getStyleClass().add("fieldCorrect");
            surnameCorrectInput = true;

            dpDate.setValue(person.getDate());
            dpDate.getEditor().getStyleClass().add("fieldCorrect");
            dateCorrectInput = true;

            SpinnerValueFactory<Integer> spinNumberPointsValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 7, person.getNumberPoints(), 1);
            this.spinNumberPoints.setValueFactory(spinNumberPointsValueFactory);
        }

        fldName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(t1.trim().isEmpty()) {
                    fldName.getStyleClass().removeAll("fieldCorrect");
                    fldName.getStyleClass().add("fieldIncorrect");
                    nameCorrectInput = false;
                } else {
                    fldName.getStyleClass().removeAll("fieldIncorrect");
                    fldName.getStyleClass().add("fieldCorrect");
                    nameCorrectInput = true;
                }
            }
        });

        fldSurname.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(t1.trim().isEmpty()) {
                    fldSurname.getStyleClass().removeAll("fieldCorrect");
                    fldSurname.getStyleClass().add("fieldIncorrect");
                    surnameCorrectInput = false;
                } else {
                    fldSurname.getStyleClass().removeAll("fieldIncorrect");
                    fldSurname.getStyleClass().add("fieldCorrect");
                    surnameCorrectInput = true;
                }
            }
        });

        dpDate.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observableValue, LocalDate localDate, LocalDate t1) {
                if (t1.isAfter(LocalDate.now()) || t1 == null) {
                    dpDate.getEditor().getStyleClass().removeAll("fieldCorrect");
                    dpDate.getEditor().getStyleClass().add("fieldIncorrect");
                    dateCorrectInput = false;
                } else {
                    dpDate.getEditor().getStyleClass().removeAll("fieldIncorrect");
                    dpDate.getEditor().getStyleClass().add("fieldCorrect");
                    dateCorrectInput = true;
                }
            }
        });

        dpDate.setConverter(new StringConverter<LocalDate>() {

            {
                dpDate.setPromptText("dd. MM. yyyy");
            }
            @Override
            public String toString(LocalDate localDate) {
                if (localDate != null) {
                    return Person.dateFormat.format(localDate);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String s) {
                if (s != null && !s.isEmpty()) {
                    return LocalDate.parse(s, Person.dateFormat);
                } else {
                    return null;
                }
            }
        });

        fldSurname.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (aBoolean != t1) {
                    lastFocusedFld = fldSurname;
                }
            }
        });

        fldName.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (aBoolean != t1) {
                    lastFocusedFld = fldName;
                }
            }
        });

        dpDate.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (aBoolean != t1) {
                    lastFocusedFld = dpDate.getEditor();
                }
            }
        });

        spinNumberPoints.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (aBoolean != t1) {
                    lastFocusedFld = spinNumberPoints.getEditor();
                }
            }
        });
    }

    private boolean validateText() {
        return dateCorrectInput && surnameCorrectInput && nameCorrectInput;
    }

    @FXML
    private void exit(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void validateInput(ActionEvent actionEvent) {
        lastFocusedFld.requestFocus();
        if(validateText()) {
            if (person == null) {
                person = new Person();
            }
            person.setName(fldName.getText());
            person.setSurname(fldSurname.getText());
            person.setNumberPoints((Integer) spinNumberPoints.getValue());
            person.setDate(dpDate.getValue());
            validated = true;
            exit(actionEvent);
        }
    }
}
