package task01;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
/*
Написать класс MathBox, реализующий следующий функционал:
Конструктор на вход получает массив Number.
Элементы не могут повторяться.
Элементы массива внутри объекта раскладываются в подходящую коллекцию (выбрать самостоятельно).
Существует метод summator, возвращающий сумму всех элементов коллекции.
Существует метод splitter, выполняющий поочередное деление всех хранящихся в объекте элементов на делитель,
являющийся аргументом метода. Хранящиеся в объекте данные полностью заменяются результатами деления.
Необходимо правильно переопределить методы toString, hashCode, equals, чтобы можно было использовать MathBox для вывода
данных на экран и хранение объектов этого класса в коллекциях (например, hashMap).
Выполнение контракта обязательно!
Создать метод, который получает на вход Integer и если такое значение есть в коллекции, удаляет его.

 */

public class MathBox {
    private final UUID id = new UUID(1l, 500l).randomUUID();
    private Set<Number> numberSet;


    public MathBox(Number[] numbers) {
        this.numberSet = new HashSet<>(Arrays.asList(numbers));

    }

    /**
     * Метод, возвращающий значение суммы всех элементов множества numberSet
     * @return сумма значений элементов множества numberSet
     */
    public Double summator() {
        AtomicReference<Double> summ = new AtomicReference<>(0d);
        numberSet.forEach(e -> summ.updateAndGet(v -> v + e.doubleValue()));
        return summ.get();
    }

    /**
     * Метод, делящий каждый элемент множества numberSet и заменяющий его полученным значением
     * @param splitter делитель
     * @return измененное методом множество numberSet
     */
    public Set splitter(int splitter) {
        Set<Number> tmpSet = new HashSet<>();
        numberSet.forEach(e -> tmpSet.add(e.doubleValue() / splitter));
        numberSet.removeAll(numberSet);
        numberSet.addAll(tmpSet);
        return numberSet;
    }

    /**
     * Метод, удаляющий элемент множества numberSet, если он равен number
     * @param number Значение, которое необходимо удалить из множества numberSet
     */
    public void remove(Integer number) {
        this.numberSet.removeIf(e-> e.intValue() == number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathBox that = (MathBox) o;
        return this.numberSet.equals(((MathBox) o).numberSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "MathBox{" +
                "id=" + id +
                ", numberSet=" + numberSet +
                '}';
    }
}
