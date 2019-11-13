package task01;

/*
Написать программу ”Hello, World!”. В ходе выполнения программы она должна выбросить исключение и завершиться с ошибкой.
Смоделировав ошибку «NullPointerException»
Смоделировав ошибку «ArrayIndexOutOfBoundsException»
Вызвав свой вариант ошибки через оператор throw
 */

public class Task01 {
    public static void main(String[] args) {
        getNullPointerException(); //NullPointer here
        getArrayIndexOutOfBoundsException(1,3,4,5,1,8,191,115,14); //ArrayIndexOutOfBounds here
        double firstArg = 10.0;
        double secondArg = 0.0;
        System.out.println(division(firstArg, secondArg)); //Arithmetic exception (own variant) here
    }

    /**
     * Сгенерировать NullPointerException
     */
    private static void getNullPointerException() {
        "".contains(null);
    }

    /**
     * Получить ArrayIndexOutOfBoundsException
     * @param numbers целые числа, аргумент переменной длины
     */
    private static void getArrayIndexOutOfBoundsException(int...numbers) {
        for (int i = 0; i < numbers.length+1; i++) {
            System.out.println(numbers[i]);
        }
    }

    /**
     * Вызов своего варианта ошибки через оператор throw. Деление на 0
     * @param firstArg делимое
     * @param secondArg делитель
     * @return результат деления firstArg на secondArg
     */
    private static double division(double firstArg, double secondArg) {
        if (secondArg == 0) throw new ArithmeticException("Division by zero");
        return firstArg / secondArg;
    }
}
