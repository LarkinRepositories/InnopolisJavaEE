package task01.pets;

import lombok.Data;
import task01.person.Person;
import java.util.UUID;

@Data
public abstract class Pet implements Comparable<Pet> {
    private final UUID ID = UUID.randomUUID();

    protected Integer weight;
    protected String name;
    protected Person owner;

    public abstract void voice();


    @Override
    public int compareTo(Pet pet) {
        int result = owner.compareTo(pet.owner);
        if (result != 0) return result;
        result = this.name.compareTo(pet.name);
        if (result != 0) return result;
        return this.weight.compareTo(pet.weight);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "Идентификационный номер=" + ID +
                ", Кличка='" + name + '\'' +
                ", Хозяин=" + owner +
                '}';
    }
}
