package ba.unsa.etf.rs;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public class AboutController {

    public void openHelp(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("At the beginning you have the opportunity to upload the quiz you were given. \nAfter creating the quiz, you will see the results and you can return to the incorrectly answered questions. \nYou save the results with the \"Save result\" option in the menu. \nThe application is easy to use and a logical sequence of steps leads you to the desired action.");
        alert.showAndWait();
    }
}
