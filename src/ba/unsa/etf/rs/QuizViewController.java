package ba.unsa.etf.rs;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

public class QuizViewController {

    private Main mainApp;
    @FXML
    private Label question;
    @FXML private RadioButton rbA;
    @FXML private RadioButton rbB;
    @FXML private RadioButton rbC;
    @FXML private RadioButton rbD;
    @FXML private RadioButton rbE;
    @FXML private RadioButton rbF;
    @FXML private ToggleGroup rbGroup;
    private RadioButton[] rB;
    @FXML private ProgressBar pB;
    @FXML private Button bNext;
    private static final boolean LOG = true;

    private void log(String s){ if (LOG) System.out.println(s); }

    public QuizViewController() {
        question = new Label("");
        rbGroup = new ToggleGroup();
        pB = new ProgressBar(0);
    }

    @FXML
    private void initialize() {
        rB = new RadioButton[6];
        rB[0] = rbA; rB[1] = rbB; rB[2] = rbC;
        rB[3] = rbD; rB[4] = rbE; rB[5] = rbF;
        rbA.setToggleGroup(rbGroup); rbB.setToggleGroup(rbGroup);
        rbC.setToggleGroup(rbGroup); rbD.setToggleGroup(rbGroup);
        rbE.setToggleGroup(rbGroup); rbF.setToggleGroup(rbGroup);
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void keyPressed(KeyEvent ke){
        log("Key getCode: " + ke.getCode());
        log("Key getText: " + ke.getText());
        switch (ke.getText()) {
            case "1": rb(rbA); break;
            case "2": rb(rbB); break;
            case "3": rb(rbC); break;
            case "4": rb(rbD); break;
            case "5": rb(rbE); break;
            case "6": rb(rbF); break;
            default:
                switch (ke.getCode().toString()) {
                    case "ENTER": getNextQuiz(); break;
                    case "RIGHT_ARROW": getNextQuiz(); break;
                    default: break;
                }
        }
    }

    private void rb(RadioButton pressed){
        if (pressed.isVisible()){
            pressed.setSelected(true);
            bNext.setDisable(false);
        }
    }

    @FXML private void rbClicked() {
        bNext.setDisable(false);
    }

    @FXML private void bNext() {
        getNextQuiz();
    }

    private void getNextQuiz() {
        String picked = ((Labeled) rbGroup.getSelectedToggle()).getText();
        mainApp.stepQuiz(picked);
    }

    public void showQuiz(String question, String[] allAnswers){
        this.question.setText(question + "?");
        this.question.setVisible(true);
        this.bNext.setDisable(true);
        for (int i = 0; i < 6; i++){
            if (i >= allAnswers.length) {
                rB[i].setText("");
                rB[i].setSelected(false);
                rB[i].setVisible(false);
            } else {
                rB[i].setText(allAnswers[i]);
                rB[i].setSelected(false);
                rB[i].setVisible(true);
            }
        }
    }

    public void setProgress(double d){
        pB.setProgress(d);
    }
}
