package ba.unsa.etf.rs;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ApplicationExtension.class)
class LoginTest {

    LoginController reset;

    @Start
    public void start(Stage primaryStage) throws Exception {
        PersonDAO dao = PersonDAO.getInstance();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        loader.setController(new LoginController());
        Parent root = loader.load();
        primaryStage.setTitle("Login");
        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(600);
        primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    boolean hasCss(TextField polje, String stil) {
        for (String s : polje.getStyleClass())
            if (s.equals(stil)) return true;
        return false;
    }

    @Test
    public void testEmptyField(FxRobot robot){
        robot.clickOn("#fldUsername");
        KeyCode ctrl = KeyCode.CONTROL;
        robot.clickOn("#fldPassword");
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        robot.lookup("#btnLogin");
        robot.clickOn("#btnLogin");
        TextField username = robot.lookup("#fldUsername").queryAs(TextField.class);
        TextField password = robot.lookup("#fldPassword").queryAs(TextField.class);
        robot.clickOn("OK");
        assertTrue(hasCss(username, "fieldIncorrect"));
        assertTrue(hasCss(password,"fieldIncorrect"));
    }

    @Test
    public void testIncorrectInformation(FxRobot robot){
        robot.clickOn("#fldUsername");
        robot.write("user123");
        robot.clickOn("#fldPassword");
        robot.write("password123");
        robot.lookup("#btnLogin");
        robot.clickOn("#btnLogin");
        TextField username = robot.lookup("#fldUsername").queryAs(TextField.class);
        TextField password = robot.lookup("#fldPassword").queryAs(TextField.class);
        robot.clickOn("OK");
        assertTrue(hasCss(username,"fieldIncorrect"));
        assertTrue(hasCss(password,"fieldIncorrect"));
    }

    @Test
    public void testCorrectInformation(FxRobot robot){
        robot.clickOn("#fldUsername");
        KeyCode ctrl = KeyCode.CONTROL;
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        robot.write("student");
        robot.clickOn("#fldPassword");
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        robot.write("student2020");
        robot.lookup("#btnLogin");
        robot.clickOn("#btnLogin");
        robot.window("List");
    }

    @Test
    public void testEmptyPassword(FxRobot robot){
        robot.clickOn("#fldUsername");
        robot.write("user123");
        robot.clickOn("#fldPassword");
        KeyCode ctrl=KeyCode.CONTROL;
        if (System.getProperty("os.name").equals("Mac OS X"))
            ctrl = KeyCode.COMMAND;
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        TextField username = robot.lookup("#fldUsername").queryAs(TextField.class);
        TextField password = robot.lookup("#fldPassword").queryAs(TextField.class);
        robot.clickOn("#btnLogin");
        robot.clickOn("OK");
        assertTrue(!hasCss(username,"fieldIncorrect"));
        assertTrue(hasCss(password,"fieldIncorrect"));
    }

    @Test
    public void testEmptyUsername(FxRobot robot){
        robot.clickOn("#fldUsername");
        KeyCode ctrl=KeyCode.CONTROL;
        robot.clickOn("#fldPassword");
        robot.write("password123");
        if (System.getProperty("os.name").equals("Mac OS X"))
            ctrl = KeyCode.COMMAND;
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        TextField username = robot.lookup("#fldUsername").queryAs(TextField.class);
        TextField password = robot.lookup("#fldPassword").queryAs(TextField.class);
        robot.clickOn("#btnLogin");
        robot.clickOn("OK");
        assertTrue(!hasCss(password,"fieldCorrect"));
        assertTrue(hasCss(username,"fieldIncorrect"));
    }
}
