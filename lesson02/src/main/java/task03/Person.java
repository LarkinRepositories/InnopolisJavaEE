package task03;


import lombok.Data;
import task03.exceptions.ExceptionChecker;
@Data
public class Person implements Comparable<Person> {
    private int age;
    private Sex sex;
    private String name;

    public Person(int age, Sex sex, String name) {
        this.age = age;
        this.sex = sex;
        this.name = name;
    }

    @Override
    public int compareTo(Person person) {
        ExceptionChecker.getInstance().sameAgeAndNameExceptionCheck(this, person);
        int result = (this.sex == person.sex) ? 0 : (this.sex == Sex.MAN ? -1 : 1);
        if (result != 0) return  result;
        if (this.age  >  person.age) return -1;
        else if (this.age < person.age) return 1;
        return this.name.compareTo(person.name);
    }


    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", sex=" + sex +
                ", name='" + name + '\'' +
                '}';
    }

}
