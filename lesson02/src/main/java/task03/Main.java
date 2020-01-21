package task03;
/*
Дан массив объектов Person.
Класс Person характеризуется полями age (возраст, целое число 0-100),
sex (пол – объект класса Sex со строковыми константами внутри MAN, WOMAN),
name (имя - строка). Создать два класса, методы которых будут реализовывать сортировку объектов.
Предусмотреть единый интерфейс для классов сортировки. Реализовать два различных метода сортировки этого массива по правилам:
первые идут мужчины
выше в списке тот, кто более старший
имена сортируются по алфавиту
Программа должна вывести на экран отсортированный список и время работы каждого алгоритма сортировки.
Предусмотреть генерацию исходного массива (10000 элементов и более).
Если имена людей и возраст совпадают, выбрасывать в программе пользовательское исключение.
 */

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Arrays;
import java.util.Random;

public class Main {
    private static final int PERSONS_COUNT = 10;

    public static void main(String[] args) {
        Person[] persons = generatePersonsArray(PERSONS_COUNT);
        System.out.println(Arrays.toString(persons));
        long t = System.currentTimeMillis();
        new BubbleSort().sort(persons);
        System.out.println(System.currentTimeMillis() - t);
        System.out.println(Arrays.toString(persons));
        long t2 = System.currentTimeMillis();
        new ArraysSort().sort(persons);
        System.out.println(System.currentTimeMillis() - t2);
        System.out.println(Arrays.toString(persons));
    }

    /**
     * Метод генерации массива объектов класса Person
     * @param personsCount количество элементов массива
     * @return массив объектов класса Person
     */
    private static Person[] generatePersonsArray(int personsCount) {
        if (personsCount <= 0) throw new ArithmeticException("personsCount <= 0");
        Person[] persons = new Person[personsCount];
        Random rnd = new Random();
        for (int i = 0; i < personsCount; i++) {
            persons[i] = new Person(
                    rnd.nextInt(100),
                    rnd.nextBoolean() ? Sex.MAN : Sex.WOMAN,
                    RandomStringUtils.random(10, true, false)
            );
        }
        return persons;
    }

}
