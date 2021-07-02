package ba.unsa.etf.rs;

import java.util.List;

public class Quiz {

    private String question;
    private String correct;
    private String[] allAnswers;
    private final static int MAX_QUESTIONS = 6;

    public Quiz(List<String> list){
        question = list.get(0);
        correct = list.get(1);

        if (list.size()-1 > MAX_QUESTIONS)
            allAnswers = new String[MAX_QUESTIONS];
        else
            allAnswers = new String[list.size()-1];

        int correctIndex = (int) (Math.random() * allAnswers.length);
        int listIndex = 2;
        for (int i=0; i < allAnswers.length; i++){
            if (i == correctIndex){
                allAnswers[i] = correct;
            } else {
                allAnswers[i] = list.get(listIndex);
                listIndex = listIndex + 1;
            }
        }
    }

    public String getQuestion() { return new String (question); }

    public String getCorrect() { return new String (correct); }

    public String[] getAllAnswers() { return allAnswers.clone(); }

    @Override
    public String toString() {
        String str = "Question: " + question;
        for (int i = 0; i < allAnswers.length; i++)
            str = str + "Answer " + i + ":" + allAnswers[i] + "\n";
        return question + "?\n"
                + "Correct:" + correct + "\n"
                + str;
    }
}
