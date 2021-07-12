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
import java.sql.SQLException;
import java.time.LocalDate;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class LoginController {

    public TextField fldUsername;
    public PasswordField fldPassword;
    private PersonDAO dao;

    public LoginController() {
        dao = PersonDAO.getInstance();
    }

    public void loginAction(ActionEvent actionEvent) throws SQLException {
        if (fldUsername.getText().isEmpty() || fldPassword.getText().isEmpty()) {

            if(fldUsername.getText().trim().isEmpty()) {
                fldUsername.getStyleClass().add("fieldIncorrect");
            } else {
                fldUsername.getStyleClass().removeAll("fieldIncorrect");
            }
            if(fldPassword.getText().trim().isEmpty()) {
                fldPassword.getStyleClass().add("fieldIncorrect");
            } else {
                fldPassword.getStyleClass().removeAll("fieldIncorrect");
            }

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while logging in.");
            alert.setContentText("You did not insert username or password, please try again.");
            alert.show();
            return;
        }

        String user = fldUsername.getText();
        String password = fldPassword.getText();
        boolean flag = dao.validate(user, password);

        if (!flag) {
            fldUsername.getStyleClass().add("fieldIncorrect");
            fldPassword.getStyleClass().add("fieldIncorrect");
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while logging in.");
            alert.setContentText("Invalid username or password. Please try again.");
            alert.show();

        } else {

            Stage stage2 = new Stage();
            Parent root;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PersonList.fxml"));
                PersonController personController = new PersonController();
                loader.setController(personController);
                root = loader.load();
                stage2.setTitle("List");
                stage2.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                stage2.setResizable(false);
                stage2.show();
                ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}