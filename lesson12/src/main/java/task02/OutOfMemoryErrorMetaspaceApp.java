package task02;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Задание 2. Доработать программу так, чтобы ошибка OutOfMemoryError возникала в Metaspace /Permanent Generation
 */
public class OutOfMemoryErrorMetaspaceApp {
    public static void main(String[] args) {
        try {
            getOutOfMemoryErrorMetaspace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод, позволяющий получить OutOfMemoryError в Metaspace
     * @throws ClassNotFoundException
     */
    private static void getOutOfMemoryErrorMetaspace() throws ClassNotFoundException {
        final int ITERATIONS = 100_000_000;
        ClassLoader loader = OutOfMemoryErrorMetaspaceApp.class.getClassLoader();
        List<Class<?>> list = new ArrayList<>();
        for (int i = 0; i < ITERATIONS; i++) {
            Class<?> clazz = loader.loadClass("java.util.ArrayList");
            list.add(clazz);
            if (!list.isEmpty() && i % 7 == 0) {
                list.remove(0);
            }
        }
    }
}
