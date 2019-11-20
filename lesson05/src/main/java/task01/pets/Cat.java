package task01.pets;

import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import task01.person.Person;

import java.util.Random;

@Data
public class Cat extends Pet {

    public Cat(int weight, String name, Person owner) {
        this.weight = weight;
        this.name = name;
        this.owner = owner;
    }

    public static Cat generateRandomCat() {
        Random rnd = new Random();
        return new Cat(
                rnd.nextInt(100),
                RandomStringUtils.random(10, true, false),
                Person.generateRandomPerson()
        );
    }

    @Override
    public void voice() {
        System.out.println("meow");
    }

    @Override
    public String toString() {
        return "Cat{" +
                "weight=" + weight +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                '}';
    }
}
