package ba.unsa.etf.rs;

import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public final class FileImport {

    private static final boolean LOG = false;

    private static void log(String s) {
        if (LOG) System.out.println(s);
    }

    public static void importQuiz(QuizModel quiz) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import Quiz");
        fileChooser.setInitialFileName("QuizFile.txt");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                readFileToQuiz(selectedFile, quiz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void readFileToQuiz(File f, QuizModel quiz) throws IOException {
        if (f.exists()) {
            log("Trying to read:\n" + f.getAbsolutePath());
            FileInputStream file = new FileInputStream(f);
            Scanner read = new Scanner(file);
            String string;
            List<String> list = new LinkedList<String>();

            while (read.hasNext()) {
                string = read.nextLine();
                log("String: " + string);
                if ((string.isEmpty()) && (!list.isEmpty())) {
                    quiz.addAQuiz(list);
                    log("List:" + list.toString());
                    list.clear();
                } else if ((!string.isEmpty()) && (!string.substring(0, 1).equals("/"))) {
                    list.add(string);
                }
            }
            quiz.addAQuiz(list);
            read.close();
            log(quiz.toString());
        } else {
            throw new IOException("File not found, aborting!");
        }
    }
}
