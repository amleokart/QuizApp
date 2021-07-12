package ba.unsa.etf.rs;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.time.LocalDate;

public class LoginController {

    public TextField fldName;
    public TextField fldUsername;
    public PasswordField fldPassword;
    public Button btnLogin;
    public CheckBox cbProfessor;
    public Label lblPassword;

    @FXML
    public void initialize() {
        fldName.getStyleClass().add("fieldIncorrect");
        fldUsername.getStyleClass().add("fieldIncorrect");
        fldPassword.getStyleClass().add("fieldIncorrect");

        fldName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (n.isEmpty()) {
                    fldName.getStyleClass().add("fieldIncorrect");
                } else {
                    fldName.getStyleClass().removeAll("fieldIncorrect");
                }
            }
        });

        fldUsername.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (n.isEmpty()) {
                    fldUsername.getStyleClass().add("fieldIncorrect");
                } else {
                    fldUsername.getStyleClass().removeAll("fieldIncorrect");
                }
            }
        });

        fldPassword.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (n.isEmpty()) {
                    fldPassword.getStyleClass().add("fieldIncorrect");
                } else {
                    fldPassword.getStyleClass().removeAll("fieldIncorrect");
                }
            }
        });
    }

    public void loginAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            PersonDAO model = PersonDAO.getInstance();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PersonList.fxml"));
            loader.setController(new PersonController(model));
            Parent root = loader.load();
            stage.setTitle("List");
            stage.setScene(new Scene(root, 600, 500));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void validProfessor(ActionEvent actionEvent) {
        if (cbProfessor.isSelected())
            fldPassword.setDisable(false);
        else
            fldPassword.setDisable(true);
    }
}