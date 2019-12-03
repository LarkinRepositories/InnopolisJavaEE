package task01;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Программа с консоли построчно считывает код метода doWork. Код не должен требовать импорта дополнительных классов.
 * После ввода пустой строки считывание прекращается и считанные строки добавляются в тело метода public void doWork() в файле SomeClass.java.
 * Файл SomeClass.java компилируется программой (в рантайме) в файл SomeClass.class.
 * Полученный файл подгружается в программу с помощью кастомного загрузчика
 * Метод, введенный с консоли, исполняется в рантайме (вызывается у экземпляра объекта подгруженного класса)
 */
public class Main {

    private static final String CLASS_PATH = "lesson09/src/main/java/task01/SomeClass.java";
    private static final String METHOD = "doWork()";

    public static void main(String[] args) {
        try {
            String code = readConsoleInput();
            implement(CLASS_PATH, METHOD, code);
            compile(CLASS_PATH);
            execute(CLASS_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Метод читает данные с консоли и преобразует все считанное в строку.
     * Считывание прекращается если передана пустая строка
     * @return строка-ввод с консоли
     * @throws IOException
     */
    private static String readConsoleInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String s;
        while (!(s = reader.readLine()).isEmpty()) {
            sb.append(s).append("\n\r");
        }
        return sb.toString();
    }

    /**
     * Метод, добавляет код в метод соответствующего класса
     * @param classPath путь к классу
     * @param method имя метода
     * @param codeBase код, который необходимо добавить
     * @throws IOException
     */
    private static void implement(String classPath, String method, String codeBase) throws IOException {
        StringBuilder sb = new StringBuilder();
        List<String> list = Files.readAllLines(Paths.get(classPath));
        list.forEach(e-> {
            if (e.contains(method)) {
                sb.append(e).append("\n");
                sb.append(codeBase);
            } else {
                sb.append(e).append("\n");
            }
        });
        FileOutputStream fos = new FileOutputStream(classPath);
        fos.write(sb.toString().getBytes());
        fos.flush();
    }

    /**
     * Метод, компилирующий байт код файла класса
     * @param path путь к файлу класса
     */
    private static void compile(String path) {
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        javaCompiler.run(System.in, System.out, System.err, Paths.get(path).toFile().getAbsolutePath());
    }

    /**
     * Метод, загружает класс из байт кода и выполняет метод
     * @param path путь к файлу .java
     */
    private static void execute(String path) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String classFilePath = path.substring(0, path.length() -5).concat(".class");
        ClassLoader classLoader = new Loader(classFilePath);
        Class<?> clazz = classLoader.loadClass("task01.SomeClass");
        Worker instanceOfWorker = (Worker) clazz.newInstance();
        instanceOfWorker.doWork();
    }
}
