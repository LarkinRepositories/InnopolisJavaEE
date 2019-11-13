package task03;

public class BubbleSort implements CanSort{
    /**
     * Пузырьковая сортировка массива объектов класса Person
     * @param persons массив объектов класса Person
     */
    @Override
    public void sort(Person[] persons) {
        for (int i = 0; i < persons.length; i++) {
            for (int j = persons.length-1; j > i; j--) {
                if (persons[i].compareTo(persons[j]) > 0) {
                    Person tmp = persons[i];
                    persons[i] = persons[j];
                    persons[j] = tmp;
                }
            }
        }
    }
}
