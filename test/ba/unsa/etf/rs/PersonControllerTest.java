package ba.unsa.etf.rs;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(ApplicationExtension.class)
class PersonControllerTest {

    Stage theStage;
    PersonDAO model;
    PersonController controller;

    @Start
    public void start(Stage stage) throws Exception {
        model = PersonDAO.getInstance();
        model.clearAll();
        model.defaultData();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PersonList.fxml"));
        controller = new PersonController(model);
        loader.setController(controller);
        Parent root = loader.load();
        stage.setTitle("Library");
        stage.setScene(new Scene(root, 600, 500));
        stage.show();
        stage.toFront();
        theStage = stage;
    }

    @Test
    public void testStatusMsg(FxRobot robot) {
        String statusMsg = robot.lookup("#statusMsg").queryAs(Label.class).getText();
        assertNotNull(statusMsg);
        assertEquals("Program started.", statusMsg);
    }

    @Test
    public void testTableViewColumns(FxRobot robot) {
        TableView tableView = robot.lookup("#tablePerson").queryAs(TableView.class);
        assertNotNull(tableView);
        ObservableList<TableColumn> columns = tableView.getColumns();
        assertEquals("Name", columns.get(0).getText());
        assertEquals("Number of points", columns.get(1).getText());
        assertEquals("Date", columns.get(2).getText());
    }
}
