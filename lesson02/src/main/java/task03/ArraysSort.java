package task03;

import java.util.Arrays;

public class ArraysSort implements CanSort {
    @Override
    public void sort(Person[] persons) {
        Arrays.sort(persons, Person::compareTo);
    }
}
