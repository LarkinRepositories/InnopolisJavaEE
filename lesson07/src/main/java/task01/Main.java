package task01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        int[] numbers = generateRandomIntArray(10);
        List<Future<Factorial>> factorials = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int number: numbers) {
            factorials.add(executorService.submit(new FactorialCallable(number)));
        }
        factorials.forEach(e -> {
            try {
                System.out.println(e.get());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (ExecutionException ex) {
                ex.printStackTrace();
            }
        });
    }

    /**
     * Метод, создающий массив случайных целых цисел
     * @param elementsCount количество элементов массива
     * @return массив случайных чисел
     */
    private static int[] generateRandomIntArray(int elementsCount) {
        Random rnd = new Random();
        int[] numbers = new int[elementsCount];
        for (int i = 0; i < elementsCount; i++) {
            numbers[i] = rnd.nextInt(15);
        }
        return numbers;
    }

}
