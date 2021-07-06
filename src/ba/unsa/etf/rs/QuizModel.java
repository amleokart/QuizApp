package ba.unsa.etf.rs;

import java.util.LinkedList;
import java.util.List;

public class QuizModel {

    private List<Quiz> quiz;
    private List<Quiz> game;
    private static final boolean LOG = false;

    private static void log(String s) {
        if (LOG) System.out.println(s);
    };

    public QuizModel() throws Exception {
        quiz = new LinkedList<Quiz>();
        FileImport.importQuiz(this);
        startGame();
        log(game.toString());
    }

    public void startGame() {
        game = (List<Quiz>) ((LinkedList<Quiz>) quiz).clone();
    }

    public void addAQuiz(List<String> list){
        quiz.add(new Quiz(list));
    }

    public String getQuestion(int index){
        return game.get(index).getQuestion();
    }

    public String getCorrect(int index){
        return game.get(index).getCorrect();
    }

    public String[] getAllAnswers(int index){
        return game.get(index).getAllAnswers();
    }

    public int getGameSize(){
        return game.size();
    }

    public int getTotalScore(){
        return quiz.size();
    }

    public int getScore(){
        return quiz.size() - game.size();
    }

    public void remove(int index){
        if (index < game.size())
            game.remove(index);
        else
            throw new MyRuntimeException("Game out of bounds, trying to remove Quiz " + index);
    }

    @Override
    public String toString() {
        String str = "QUIZ\n";
        for (int i = 0; i < quiz.size(); i++){
            str = str + (i+1) + ": " + quiz.get(i).toString() + "\n";
        }
        return str;
    }
}
