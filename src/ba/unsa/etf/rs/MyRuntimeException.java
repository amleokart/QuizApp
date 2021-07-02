package ba.unsa.etf.rs;

import javafx.scene.control.Alert;

public class MyRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MyRuntimeException (String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("A runtime exception occurred!");
        alert.setContentText(message);
        alert.showAndWait();
    }
}