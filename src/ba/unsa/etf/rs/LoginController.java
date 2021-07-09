package ba.unsa.etf.rs;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController {

    public TextField usernameFld;
    public PasswordField passwordFld;
    public Button loginBtn;

    @FXML
    public void initialize() {
        usernameFld.getStyleClass().add("fieldIncorrect");

        usernameFld.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (n.isEmpty()) {
                    usernameFld.getStyleClass().add("fieldIncorrect");
                } else {
                    usernameFld.getStyleClass().removeAll("fieldIncorrect");
                }
            }
        });
    }

    public void loginAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        if (usernameFld.getText() == "Elma" && passwordFld.getText() == "Telma") {
            try {
                PersonDAO model = PersonDAO.getInstance();
                Locale.setDefault(new Locale("en", "US"));
                ResourceBundle bundle = ResourceBundle.getBundle("languages");
                //FXMLLoader loader = new FXMLLoader();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PersonList.fxml"), bundle);
                LoginController ctrl = new LoginController();
                //FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PersonList.fxml"));
                loader.setController(new PersonController(model));
                Parent root = loader.load();
                stage.setTitle("List");
                stage.setScene(new Scene(root, 600, 500));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Greska!");
            alert.showAndWait();
        }
    }

    public void mouse(MouseEvent mouseEvent) {
    }
}
