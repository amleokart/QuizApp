package ba.unsa.etf.rs;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController {

    public TextField usernameFld;
    public Button loginBtn;

    @FXML
    public void initialize() {
        usernameFld.getStyleClass().add("poljeNijeIspravno");

        usernameFld.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (n.isEmpty()) {
                    usernameFld.getStyleClass().add("poljeNijeIspravno");
                } else {
                    usernameFld.getStyleClass().removeAll("poljeNijeIspravno");
                }
            }
        });
    }

    public void loginAction(ActionEvent actionEvent) {
    }

    public void mouse(MouseEvent mouseEvent) {
    }
}
