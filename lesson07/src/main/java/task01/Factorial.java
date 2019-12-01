package task01;

import lombok.Data;

import java.math.BigInteger;
import java.util.concurrent.Callable;


@Data
public class Factorial {
    private int number;
    private BigInteger factorial;

    public Factorial() {

    }

    Factorial(int number, BigInteger factorial) {
        this.number = number;
        this.factorial = factorial;
    }


    /**
     * Метод, вычисляющий факториал числа number
     * @param number число, факториал которого необходимо вычислить
     * @return факториал числа number
     */
    public static BigInteger factorial(int number) {
        BigInteger factorial = BigInteger.ONE;
        for (int i = 2; i <= number; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }
}
