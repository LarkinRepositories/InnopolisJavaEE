package task01;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Path inputFile = Paths.get("/input.txt");
        Path outputFile = Paths.get("/output.txt");
        try {
            FileUtils.writeWordsToFile(outputFile, FileUtils.readWords(inputFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
