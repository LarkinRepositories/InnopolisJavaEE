package task01.person;

import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

@Data
public class Person implements Comparable<Person> {
    private String name;
    private int age;
    private Gender gender;

    private Person(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public static Person generateRandomPerson() {
        Random rnd = new Random();
        return new Person(
                RandomStringUtils.random(10, true, false),
                rnd.nextInt(100),
                rnd.nextBoolean() ? Gender.MALE : Gender.FEMALE
        );
    }

    @Override
    public int compareTo(Person person) {
        int result = (this.gender == person.gender) ? 0 : (this.gender == Gender.MALE ? -1 : 1);
        if (result != 0) return  result;
        if (this.age  >  person.age) return -1;
        else if (this.age < person.age) return 1;
        return this.name.compareTo(person.name);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
