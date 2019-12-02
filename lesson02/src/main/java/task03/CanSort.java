package task03;

import java.util.List;

public interface CanSort {
    /**
     * Абстрактный метод, который должен осуществлять сортировку.
     * @param persons массив объектов класса Person
     * @return отсортированный список (коллекция, реализующая интерфейс List) объектов класса Person
     */
    void sort(Person[] persons);
}
