package ba.unsa.etf.rs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

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
    }

    public void changeLanguage(ActionEvent actionEvent) {
    }
}
