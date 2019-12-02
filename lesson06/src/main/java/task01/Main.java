package task01;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Path inputFile = Paths.get("lesson06/src/main/resources/task01/input.txt");
        Path outputFile = Paths.get("lesson06/src/main/resources/task01/output.txt");
        try {
            FileUtils.writeWordsToFile(outputFile, FileUtils.readWords(inputFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
