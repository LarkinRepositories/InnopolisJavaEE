package task02;

/*
Задание 2. Составить программу, генерирующую N случайных чисел.
Для каждого числа k вычислить квадратный корень q.
Если квадрат целой части q числа равен k, то вывести это число на экран.
Предусмотреть что первоначальные числа могут быть отрицательные, в этом случае генерировать исключение.
 */

import java.util.ArrayList;
import java.util.List;

public class Task02 {
    public static void main(String[] args) {
        calculations(generateDigitsList(1000));
    }

    /**
     * Метод для генерации списка, состоящиего из случайных чисел от -500 до 500
     * @param digitsCount количество чисел, которые будут сгенерированы
     * @return список сгенерированных чисел
         */
        private static List<Double> generateDigitsList(int digitsCount) {
            List<Double> digits = new ArrayList<>();
        for (int i = 0; i < digitsCount; i++) {
            digits.add(Math.random() * 500 * (Math.random() > 0.5 ? 1 : -1));
        }
        return digits;
    }

    /**
     * Метод, выполняющий вычисление квадратного корня для чисел списка и выводящий число на экран,
     * если квадрат целой части его квадратного корня равен числу.
     * @param digitsList список чисел
     */
    private static void calculations(List<Double> digitsList) {
        if (digitsList != null) {
            digitsList.forEach(e -> {
                if ( Math.floor(Math.pow(Math.sqrt(e), 2)) == e) System.out.println(e);
                else if (e < 0) throw new ArithmeticException();
            });
        }
    }
}
