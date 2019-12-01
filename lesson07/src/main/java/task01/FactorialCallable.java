package task01;

import java.util.concurrent.Callable;

public class FactorialCallable implements Callable<Factorial> {
    private int number;

    FactorialCallable(int number) {
        this.number = number;
    }

    @Override
    public Factorial call() throws Exception {
        return new Factorial(number, Factorial.factorial(number));
    }
}
