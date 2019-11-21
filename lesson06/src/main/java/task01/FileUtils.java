package task01;

/*
Написать программу, читающую текстовый файл.
Программа должна составлять отсортированный по алфавиту список слов, найденных в файле и сохранять его в файл-результат.
Найденные слова не должны повторяться, регистр не должен учитываться. Одно слово в разных падежах – это разные слова.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

class FileUtils {

    static Set<String> readWords(Path inputFile) throws IOException {
        BufferedReader wordsReader = Files.newBufferedReader(inputFile);
        Set<String> wordsRead = new TreeSet<>();
        String s;
        while ((s = wordsReader.readLine()) != null) {
            wordsRead.addAll(Arrays.asList(s.split("\\s+")));
        }
        return wordsRead;
    }

    static void writeWordsToFile(Path outputFile, Set<String> words) throws IOException {
        if (outputFile != null) {
            BufferedWriter writer = Files.newBufferedWriter(outputFile);
            words.forEach(e -> {
                try {
                    writer.write(e);
                    writer.newLine();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        }
    }
}
