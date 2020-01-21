package task01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Задание 1. Необходимо создать программу, которая продемонстрирует утечку памяти в Java.
 * При этом объекты должны не только создаваться, но и периодически частично удаляться,
 * чтобы GC имел возможность очищать часть памяти.
 * Через некоторое время программа должна завершиться с ошибкой OutOfMemoryError c пометкой Java Heap Space.
**/

public class OutOfMemoryErrorApp {
    public static void main(String[] args) {
        getOutOfMemoryError();
    }

    /**
     * Метод, создает массив Integer в цикле, с каждой итерацией в 100 раз больше предыдущего
     * переодически (при выполнении условия random.nextInt() % 2 == 0 элемент массива становится null под очистку Garbage collector
     */
    private static void getOutOfMemoryError() {
        final int LIMIT = 100;
        int multiplier = 100;
        Random random = new Random();
        for (int i = 0; i < LIMIT; i ++) {
            Integer[] numbers = new Integer[multiplier];
            numbers[i] = random.nextInt();
            if (random.nextInt() % 2 == 0) numbers[i] = null;
            multiplier *= multiplier;
        }
    }
}
