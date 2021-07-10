package ba.unsa.etf.rs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private QuizModel model;
    private QuizViewController view;
    private LoadViewController load;
    private MenuController menu;
    private int currentIndex;
    private int attempt;
    private static final boolean LOG = true;

    private static void log(String s) {
        if (LOG) System.out.println(s);
    };

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Quiz");
        showMenu();
        showLoadView();
    }

    private void showMenu() {
        try {
            /*
            Locale.setDefault(new Locale("bs", "BA"));
            ResourceBundle bundle = ResourceBundle.getBundle("languages");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/glavna.fxml"), bundle);
             */
            Locale.setDefault(new Locale("en", "US"));
            ResourceBundle bundle = ResourceBundle.getBundle("languages");
            //FXMLLoader loader = new FXMLLoader();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Menu.fxml"), bundle);
            MenuController ctrl = new MenuController();
            loader.setController(ctrl);
            //loader.setLocation(Controller.class.getResource("/fxml/Menu.fxml"), bundle);
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            MenuController viewController = loader.getController();
            viewController.setMainApp(this);
            menu = viewController;
            primaryStage.show();
        } catch (IOException e) {
            throw new MyRuntimeException(e.getMessage());
        }
    }

    private void showLoadView() {
        try {
            Locale.setDefault(new Locale("en", "US"));
            ResourceBundle bundle = ResourceBundle.getBundle("languages");
            //FXMLLoader loader = new FXMLLoader();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoadView.fxml"), bundle);
            LoadViewController ctrl = new LoadViewController();
            loader.setController(ctrl);
            /*FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Controller.class.getResource("/fxml/LoadView.fxml"));*/
            AnchorPane loadView = (AnchorPane) loader.load();
            rootLayout.setCenter(loadView);
            LoadViewController viewController = loader.getController();
            viewController.setMainApp(this);
            load = viewController;
        } catch (IOException e) {
            throw new MyRuntimeException(""+e.getMessage());
        }
    }

    public void stepQuiz(String pick){
        if (pick.equals(model.getCorrect(currentIndex)))
            model.remove(currentIndex);
        else
            currentIndex = currentIndex + 1;
        if (currentIndex < model.getGameSize()){
            showQuiz((currentIndex));
        } else {
            showResult();
        }
    }

    private void showResult(){
        showLoadView();
        showLoadView();
        menu.enableRestartMenuItem();
        load.showResult(model.getScore(), model.getTotalScore(), attempt);
    }

    public void resumeQuiz(){
        showQuizView();
    }

    public void restartQuiz() {
        attempt = 0;
        model.startGame();
        showQuizView();
    }

    public void loadQuiz() throws Exception{
        attempt = 0;
        this.model = new QuizModel();
        showQuizView();
    }

    private void showQuizView() {
        currentIndex = 0;
        attempt = attempt + 1;
        if (model.getGameSize() > 0) {
            try {
                Locale.setDefault(new Locale("en", "US"));
                ResourceBundle bundle = ResourceBundle.getBundle("languages");
                //FXMLLoader loader = new FXMLLoader();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/QuizView.fxml"), bundle);
                QuizViewController ctrl = new QuizViewController();
                loader.setController(ctrl);
                /*FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Controller.class.getResource("/fxml/QuizView.fxml"));*/
                AnchorPane quizView = (AnchorPane) loader.load();
                rootLayout.setCenter(quizView);
                QuizViewController viewController = loader.getController();
                viewController.setMainApp(this);
                view = viewController;
                showQuiz(currentIndex);
            } catch (IOException e) {
                throw new MyRuntimeException(e.getMessage());
            }
        }
    }

    private void showQuiz(int index){
        view.setProgress((double) (1 + currentIndex + model.getScore()) / (double) model.getTotalScore());
        if (index < model.getGameSize())
            view.showQuiz(model.getQuestion(index), model.getAllAnswers(index));
        else
            throw new MyRuntimeException("Game is out of bounds: " + index + " of " + model.getGameSize());
    }
}
