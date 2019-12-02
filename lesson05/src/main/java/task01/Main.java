package task01;

import task01.exceptions.DuplicateEntryException;
import task01.exceptions.NullPetException;
import task01.person.Person;
import task01.pets.Cat;
import task01.pets.Dog;
import task01.storage.PetStorage;

import java.util.Random;

public class Main {
    private static int PETS_COUNT = 10;

    public static void main(String[] args) {
        try {
            Random rnd = new Random();
            for (int i = 0; i < PETS_COUNT; i++) {
               PetStorage.add( rnd.nextBoolean() ? Dog.generateRandomDog() : Cat.generateRandomCat());
            }
            Dog dog = new Dog(27, "Puppy", Person.generateRandomPerson());
            PetStorage.add(dog);
            System.out.println(PetStorage.display());
            System.out.println(PetStorage.search("Puppy"));
            PetStorage.update(dog.getID(), Dog.generateRandomDog());
            System.out.println(PetStorage.display());
            System.out.println(PetStorage.search("Puppy"));
        } catch (DuplicateEntryException | NullPetException e) {
            e.printStackTrace();
        }
    }
}
