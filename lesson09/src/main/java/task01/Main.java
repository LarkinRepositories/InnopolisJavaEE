package task01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Программа с консоли построчно считывает код метода doWork. Код не должен требовать импорта дополнительных классов.
 * После ввода пустой строки считывание прекращается и считанные строки добавляются в тело метода public void doWork() в файле SomeClass.java.
 * Файл SomeClass.java компилируется программой (в рантайме) в файл SomeClass.class.
 * Полученный файл подгружается в программу с помощью кастомного загрузчика
 * Метод, введенный с консоли, исполняется в рантайме (вызывается у экземпляра объекта подгруженного класса)
 */
public class Main {
    public static void main(String[] args) {

    }

    private static String readConsoleInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String s;
        while (!(s = reader.readLine()).isEmpty()) {
            sb.append("\n\r");
        }
        return sb.toString();
    }

    private static void implement(String classPath, String method, String codeBase) throws IOException {


    }

    private static void compile(String path) {

    }

    private static void execute(String path) {

    }
}
