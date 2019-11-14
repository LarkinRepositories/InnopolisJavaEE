package task03;

import lombok.Data;


import java.util.ArrayList;
import java.util.Collection;

/*
Создать класс ObjectBox, который будет хранить коллекцию Object.
У класса должен быть метод addObject, добавляющий объект в коллекцию.
У класса должен быть метод deleteObject, проверяющий наличие объекта в коллекции и при наличии удаляющий его.
Должен быть метод dump, выводящий содержимое коллекции в строку.
 */
@Data
public class ObjectBox<T> {
    private Collection<T> objects;


    public ObjectBox() {
        objects = new ArrayList<>();
    }

    public ObjectBox(Collection<T> objects) {
        this.objects = objects;
    }

    public void addObject(T object) {
        objects.add(object);
    }

    public boolean deleteObject(T object) {
        if (objects.contains(object)) {
            objects.remove(object);
            return true;
        }
        return false;
    }

    public String dump() {
        StringBuilder sb = new StringBuilder();
        objects.forEach(e -> sb.append(e.toString()).append(" "));
        return sb.toString();
    }

}

