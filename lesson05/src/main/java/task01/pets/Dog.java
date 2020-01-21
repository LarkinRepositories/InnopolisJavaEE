package task01.pets;

import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import task01.person.Person;

import java.util.Random;

@Data
public class Dog extends Pet {
    public Dog(int weight, String name, Person owner) {
        this.weight = weight;
        this.name = name;
        this.owner = owner;
    }

    public static Dog generateRandomDog() {
        Random rnd = new Random();
        return new Dog(
                rnd.nextInt(100),
                RandomStringUtils.random(10, true, false),
                Person.generateRandomPerson()
        );
    }

    @Override
    public void voice() {
        System.out.println("Bark bark");
    }

    @Override
    public String toString() {
        return "Dog{" +
                "weight=" + weight +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                '}';
    }
}
