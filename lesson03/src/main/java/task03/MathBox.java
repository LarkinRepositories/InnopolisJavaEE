package task03;

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

public class MathBox<T extends Number> extends ObjectBox<T> {
    private final UUID id = UUID.randomUUID();


    public MathBox(Number[] numbers) {
        super(new HashSet<>((Collection<? extends T>) Arrays.asList(numbers)));

    }

    @Override
    public void addObject(T number) {
        if (number.getClass().toString().equals("java.lang.Object")) {
            throw new IllegalArgumentException("You cannot add exact Object class exemplar here");
        }
        super.addObject(number);
    }

    /**
     * Метод, возвращающий значение суммы всех элементов множества numberSet
     * @return сумма значений элементов множества numberSet
     */
    public Double summator() {
        AtomicReference<Double> sum = new AtomicReference<>(0d);
        getObjects().forEach(e -> sum.updateAndGet(v -> v + e.doubleValue()));
        return sum.get();
    }

    /**
     * Метод, делящий каждый элемент множества numberSet и заменяющий его полученным значением
     * @param splitter делитель
     * @return измененное методом множество numberSet
     */
    public Collection<T> splitter(int splitter) {
        Set<Number> tmpSet = new HashSet<>();
        getObjects().forEach(e ->
                tmpSet.add(e.doubleValue() / splitter)
        );
        setObjects((Collection<T>) tmpSet);
        return getObjects();
    }

    /**
     * Метод, удаляющий элемент множества numberSet, если он равен number
     * @param number Значение, которое необходимо удалить из множества numberSet
     */
    public void remove(Integer number) {
        this.getObjects().removeIf(e-> e.intValue() == number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathBox that = (MathBox) o;
        return this.getObjects().equals(((MathBox) o).getObjects());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "MathBox{" +
                "id=" + id +
                ", numberSet=" + getObjects()  +
                '}';
    }
}
