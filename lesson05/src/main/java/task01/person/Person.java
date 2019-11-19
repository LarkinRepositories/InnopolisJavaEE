package task01.person;

import lombok.Data;

@Data
public class Person implements Comparable<Person> {
    private String name;
    private int age;
    private Gender gender;


    @Override
    public int compareTo(Person person) {
        int result = (this.gender == person.gender) ? 0 : (this.gender == Gender.MALE ? -1 : 1);
        if (result != 0) return  result;
        if (this.age  >  person.age) return -1;
        else if (this.age < person.age) return 1;
        return this.name.compareTo(person.name);
    }
}
