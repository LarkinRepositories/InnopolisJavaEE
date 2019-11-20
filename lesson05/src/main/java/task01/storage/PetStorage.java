package task01.storage;

import task01.exceptions.DuplicateEntryException;
import task01.exceptions.NullPetException;
import task01.pets.Pet;

import java.util.*;
import java.util.stream.Collectors;

/*
метод добавления животного в общий список (учесть, что добавление дубликатов должно приводить к исключительной ситуации)
поиск животного по его кличке (поиск должен быть эффективным)
изменение данных животного по его идентификатору
вывод на экран списка животных в отсортированном порядке. Поля для сортировки –  хозяин, кличка животного, вес.
 */
public class PetStorage {

    private static Map<UUID, Pet> storage = new HashMap<>();
    private static Map<String, Set<Pet>> petNames = new HashMap<>();


    /**
     * Метод добавления питомцев в справочник
     * @param pet Объект класса Pet, который добавляем в картотеку
     * @throws DuplicateEntryException
     */
    public static void add(Pet pet) throws DuplicateEntryException {
        if (storage.containsKey(pet.getID())) throw new DuplicateEntryException("This pet already exists");
        storage.put(pet.getID(), pet);
        addName(pet);
    }

    /**
     * Изменяем данные животного по его идентификатору
     * @param petID идентификатор животного
     * @param pet  на что хотим заменить.
     * @throws NullPetException если животного не существует
     */
    public static void update(UUID petID, Pet pet) throws NullPetException {
        if (storage.get(petID) == null) throw new NullPetException(pet.toString() + "does not exist");
        Pet tmpPet = storage.get(pet.getID());
        if (!pet.equals(tmpPet)) {
            removeName(storage.get(petID));
            addName(pet);
            storage.put(pet.getID(), pet);
        }
    }

    /**
     * Метод выводит отсортированный список животных
     * @return отсортированный список животных
     */
    public static List<Pet> display() {
        return storage.values().stream().sorted(Pet::compareTo).collect(Collectors.toList());
    }

    /**
     * Поиск животного по кличке
     * @param name кличка
     * @return множество животных с такой кличкой
     */
    public static Set<Pet> search(String name) {
        return petNames.getOrDefault(name, Collections.emptySet());
    }

    /**
     * Добавляем кличку питомца в колллекцию кличек
     * @param pet Экземпляр класса pet, который будет добавлен
     */
    private static void addName(Pet pet) {
        petNames.computeIfAbsent(pet.getName(), e-> new TreeSet<>()).add(pet);
    }

    /**
     * Удаляем кличку из коллекции
     * @param pet Экземпляр класса pet, который будет удален
     */
    private static void removeName(Pet pet) {
        petNames.get(pet.getName()).remove(pet);
    }
}
