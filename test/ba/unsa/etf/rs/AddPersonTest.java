package ba.unsa.etf.rs;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.time.LocalDate;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
class AddPersonTest {

    Stage theStage;
    EditPersonController controller;

    @Start
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EditPerson.fxml"));
        controller = new EditPersonController(null);
        loader.setController(controller);
        Parent root = loader.load();
        stage.setTitle("Edit person");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
        stage.toFront();
        theStage = stage;
    }

    @Test
    public void testName (FxRobot robot) {
        robot.clickOn("#fldName");
        robot.write("Elma");
        TextField name = robot.lookup("#fldName").queryAs(TextField.class);
        Background bg = name.getBackground();
        Paint yellowgreen = Paint.valueOf("#adff2f");
        boolean colorFound = false;
        for (BackgroundFill bf : bg.getFills())
            if (bf.getFill().equals(yellowgreen))
                colorFound = true;
        assertTrue(colorFound);
        robot.press(KeyCode.BACK_SPACE).release(KeyCode.BACK_SPACE);
        robot.press(KeyCode.BACK_SPACE).release(KeyCode.BACK_SPACE);
        robot.press(KeyCode.BACK_SPACE).release(KeyCode.BACK_SPACE);
        robot.press(KeyCode.BACK_SPACE).release(KeyCode.BACK_SPACE);
        bg = name.getBackground();
        Paint lightpink = Paint.valueOf("#ffb6c1");
        colorFound = false;
        for (BackgroundFill bf : bg.getFills())
            if (bf.getFill().equals(lightpink))
                colorFound = true;
        assertTrue(colorFound);

        robot.write("Elm");
        bg = name.getBackground();
        colorFound = false;
        for (BackgroundFill bf : bg.getFills())
            if (bf.getFill().equals(yellowgreen))
                colorFound = true;
        assertTrue(colorFound);
    }

    @Test
    public void testSurname(FxRobot robot) {
        robot.clickOn("#fldSurname");
        robot.write("Trako");
        TextField surname = robot.lookup("#fldSurname").queryAs(TextField.class);
        Background bg = surname.getBackground();
        Paint yellowgreen = Paint.valueOf("#adff2f");
        boolean colorFound = false;
        for (BackgroundFill bf : bg.getFills())
            if (bf.getFill().equals(yellowgreen))
                colorFound = true;
        assertTrue(colorFound);
        robot.press(KeyCode.BACK_SPACE).release(KeyCode.BACK_SPACE);
        robot.press(KeyCode.BACK_SPACE).release(KeyCode.BACK_SPACE);
        robot.press(KeyCode.BACK_SPACE).release(KeyCode.BACK_SPACE);
        robot.press(KeyCode.BACK_SPACE).release(KeyCode.BACK_SPACE);
        robot.press(KeyCode.BACK_SPACE).release(KeyCode.BACK_SPACE);
        bg = surname.getBackground();
        Paint lightpink = Paint.valueOf("#ffb6c1");
        colorFound = false;
        for (BackgroundFill bf : bg.getFills())
            if (bf.getFill().equals(lightpink))
                colorFound = true;
        assertTrue(colorFound);
        robot.write("E");
        bg = surname.getBackground();
        colorFound = false;
        for (BackgroundFill bf : bg.getFills())
            if (bf.getFill().equals(yellowgreen))
                colorFound = true;
        assertTrue(colorFound);
    }


    @Test
    public void spinnerTest(FxRobot robot) {
        robot.clickOn("#fldName");
        robot.write("Elma");
        robot.clickOn("#fldSurname");
        robot.write("Trako");
        robot.press(KeyCode.TAB).release(KeyCode.TAB);
        robot.press(KeyCode.UP).release(KeyCode.UP);
        Spinner kbs = robot.lookup("#spinNumberPoints").queryAs(Spinner.class);
        assertNotNull(kbs);
        Integer i = (Integer) kbs.getValueFactory().getValue();
        robot.clickOn("#btnOk");
        assertFalse(theStage.isShowing());
        Person person = controller.getPerson();
        assertEquals("Elma", person.getName());
        assertEquals("Trako", person.getSurname());
        assertEquals(1, person.getNumberPoints());
        assertEquals(LocalDate.now(), person.getDate());
        assertEquals(new Integer(1), i);
    }

    @Test
    public void testDate(FxRobot robot) {
        robot.clickOn("#fldName");
        robot.write("Elma");
        robot.clickOn("#fldSurname");
        robot.write("Trako");
        robot.press(KeyCode.TAB).release(KeyCode.TAB);
        robot.press(KeyCode.TAB).release(KeyCode.TAB);
        robot.clickOn("#dpDate");
        KeyCode ctrl = KeyCode.CONTROL;
        if (System.getProperty("os.name").equals("Mac OS X"))
            ctrl = KeyCode.COMMAND;
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.write("23. 05. 1998");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        DatePicker datePicker = robot.lookup("#dpDate").queryAs(DatePicker.class);
        assertNotNull(datePicker);
        String date = datePicker.getEditor().getText();
        robot.clickOn("#btnOk");
        assertFalse(theStage.isShowing());
        assertEquals("23. 05. 1998", date);
        Person person = controller.getPerson();
        assertEquals(LocalDate.of(1998, 5, 23), person.getDate());
    }
}