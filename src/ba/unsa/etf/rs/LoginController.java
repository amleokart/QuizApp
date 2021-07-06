package ba.unsa.etf.rs;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

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

    public void mouse(MouseEvent mouseEvent) {
    }
}
