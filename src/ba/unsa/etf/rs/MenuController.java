package ba.unsa.etf.rs;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class MenuController {

    private Main mainApp;
    @FXML
    private MenuItem mIRestart;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML private void restartGame() throws Exception{ mainApp.restartQuiz(); }

    @FXML private void loadNewGame() throws Exception{ mainApp.loadQuiz(); }

    @FXML private void quit() { System.exit(0); }

    public void enableRestartMenuItem(){
        mIRestart.setVisible(true);
        mIRestart.setDisable(false);
    }

    public void saveResult(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Controller.class.getResource("/fxml/Login.fxml"));
            LoginController ctrl = new LoginController();
            loader.setController(ctrl);
            Parent root = loader.load();
            stage.setTitle("Login");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void openAbout(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Controller.class.getResource("/fxml/About.fxml"));
            AboutController ctrl = new AboutController();
            loader.setController(ctrl);
            Parent root = loader.load();
            stage.setTitle("Help");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeLanguage(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText("This feature is still under construction!");
        alert.showAndWait();
    }
}
