package task02;
/*
Задание 2. Создать генератор текстовых файлов, работающий по следующим правилам:
Предложение состоит из 1<=n1<=15 слов. В предложении после произвольных слов могут находиться запятые.
Слово состоит из 1<=n2<=15 латинских букв
Слова разделены одним пробелом
Предложение начинается с заглавной буквы
Предложение заканчивается (.|!|?)+" "
Текст состоит из абзацев. в одном абзаце 1<=n3<=20 предложений. В конце абзаца стоит разрыв строки и перенос каретки.
Есть массив слов 1<=n4<=1000. Есть вероятность probability вхождения одного из слов этого массива в следующее предложение (1/probability).
Необходимо написать метод getFiles(String path, int n, int size, String[] words, int probability),
который создаст n файлов размером size в каталоге path. words - массив слов, probability - вероятность.
 */

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class FileGenerator {

    private static final Random RND = new Random();
    private static final int WORDS_COUNT = 1000;

    public static void main(String[] args) {
        String[] words = new String[WORDS_COUNT];
        for (int i  = 0; i < WORDS_COUNT; i++) {
            words[i] = generateRandomWord(false);
        }
        int probability = 5;
        int size = 30;
        try {
            getFiles("temp", 3, size, words, probability);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод, создает n файлов с размером size в каталоге path
     * @param path путь, где будут созданы файлы
     * @param n количество своздаваемых файлов
     * @param size размер создаваемых файлов
     * @param words массив слов
     * @param probability вероятность вхождения слов из массива words в файл
     */
    public static void getFiles(String path, int n, int size, String[] words, int probability) throws IOException {
        Path dir = Files.createTempDirectory(path);
        int fileSize = 0;
        for (int i = 0; i < n; i++) {
            Path file = dir.resolve(String.format("file%s.txt", i));
            Files.createFile(file);
            BufferedWriter writer = Files.newBufferedWriter(file);
            List<String> paragraph = generateParagraph(words, probability);
            while (fileSize < size) {
                paragraph.forEach(e ->
                        {
                            try {
                                writer.write(e);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                );
                for (int j = 0; j < paragraph.size(); j++)  {
                    fileSize += paragraph.get(i).length();
                }
            }
            writer.close();
        }
    }

    /**
     * Метод генератор случайного "слова"
     * @param capitalize true = слово начинается с заглавной буквы , false = со строчной
     * @return сгенерированное слово
     */
    private static String generateRandomWord(boolean capitalize) {
        final int minLength = 1;
        final int maxLength = 15;
        String word = RandomStringUtils.random(minLength + RND.nextInt(maxLength), true, false);
        return capitalize ? word.substring(0, 1).toUpperCase() + word.substring(1) : word;
    }

    /**
     * Метод-генератор случайного предложения
     * @param words массив слов для вхождения
     * @param probability вероятность вхождения слов из массива words
     * @return Строка-предложение
     */
    private static String generateRandomSentence(String[] words, int probability) {
        final int minLength = 1;
        final int maxLength = 15;
        List<String> sentence = new ArrayList<>();
        sentence.add(generateRandomWord(true));
        sentence.add(RND.nextBoolean() ? ", "  : " ");
        for (int i = 0; i < minLength + RND.nextInt(maxLength); i++) {
            if (RND.nextFloat() <  1.0f / probability) {
                sentence.add(words[RND.nextInt(words.length)]);
                sentence.add(RND.nextBoolean() ? ", " : " ");
            }
            sentence.add(generateRandomWord(false));
            sentence.add(RND.nextBoolean() ? ", " : " ");
        }
         sentence.set(
                 sentence.size()-1,
                 sentence.get(sentence.size()-1).replaceAll("(, | )", generateRandomEndOfSentence()
                 ));
        return sentence.stream().reduce("", StringUtils::join);
    }

    /**
     * Метод-генератор случайного окончания предложения
     * @return Строка-окончание
     */
    private static String generateRandomEndOfSentence() {
        char[] ends = {'!', '.', '?'};
        return ends[RND.nextInt(ends.length-1)] + " ";
    }

    /**
     * Метод генератор параграфа (абзаца)
     * @param words массив слов для генерации предложений
     * @param probability вероятность вхождения слов из массива words при генерации предложений
     * @return список предложений абзаца и перенос каретки.
     */
    private static List<String> generateParagraph(String[] words, int probability) {
        final int minLength = 1;
        final int maxLength = 20;
        List<String> paragraph = new LinkedList<>();
        for (int i = 0; i < minLength + RND.nextInt(maxLength); i++) {
            paragraph.add(generateRandomSentence(words, probability));
        }
        paragraph.add("\n");
        return paragraph;
    }

}
