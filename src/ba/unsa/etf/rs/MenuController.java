package ba.unsa.etf.rs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class MenuController {

    private Main mainApp;
    @FXML
    private MenuItem mIRestart;
    private String[] lans = new String[2];
    private String currentLan;

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
            Locale.setDefault(new Locale("en", "US"));
            ResourceBundle bundle = ResourceBundle.getBundle("languages");
            //FXMLLoader loader = new FXMLLoader();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"), bundle);
            LoginController ctrl = new LoginController();
            //FXMLLoader loader = new FXMLLoader();
            //loader.setLocation(Controller.class.getResource("/fxml/Login.fxml"));
            //LoginController ctrl = new LoginController();
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
            Locale.setDefault(new Locale("en", "US"));
            ResourceBundle bundle = ResourceBundle.getBundle("languages");
            //FXMLLoader loader = new FXMLLoader();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/About.fxml"), bundle);
            AboutController ctrl = new AboutController();
            //FXMLLoader loader = new FXMLLoader();
            //loader.setLocation(Controller.class.getResource("/fxml/Login.fxml"));
            //LoginController ctrl = new LoginController();
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
        if (Locale.getDefault().getCountry().equals("BA")) currentLan = lans[0];
        else currentLan = lans[1];
        Dialog d = new ChoiceDialog<>(currentLan, lans);
        ResourceBundle bundle = ResourceBundle.getBundle("languages");
        d.setTitle(bundle.getString("languageChoice"));
        d.setHeaderText(bundle.getString("pleaseChooseLan"));
        d.setContentText(bundle.getString("language") + ":");
        d.showAndWait();
        if (currentLan != ((ChoiceDialog) d).getSelectedItem().toString()) {
            if (currentLan.equals(lans[0])) currentLan = lans[1];
            else currentLan = lans[0];
            if (currentLan.equals("English")) {
                Locale.setDefault(new Locale("en", "US"));
                System.out.println("en");
            } else {
                Locale.setDefault(new Locale("bs", "BA"));
                System.out.println("bs");
            }
        }
    }
}
