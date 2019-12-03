package task01;

import lombok.Getter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.reflect.Field;
import java.util.List;

/*
Необходимо разработать класс, реализующий следующие методы:
void serialize (Object object, String file);
Object deSerialize(String file);
Методы выполняют сериализацию объекта Object в файл file и десериализацию объекта из этого файла.
Обязательна сериализация и десериализация "плоских" объектов (все поля объекта - примитивы, или String).
 */

public class Serializator {
    /**
     * Метод сериализует объект object в файл file
     * @param object объект для сериализации
     * @param file пусть до файла
     * @throws IOException
     * @throws IllegalAccessException
     */
    public static void serialize(Object object, String file) throws IOException, IllegalAccessException {
        try (DataOutputStream dos = new DataOutputStream(Files.newOutputStream(Paths.get(file)))) {
            for (Field f : object.getClass().getDeclaredFields()) {
                if (Modifier.isPrivate(f.getModifiers())) f.setAccessible(true);
                switch (f.getType().getTypeName()) {
                    case ("java.lang.String") :
                        dos.writeUTF((String) f.get(object));
                        break;
                    case ("char") :
                         dos.writeChar(f.getChar(object));
                    break;
                    case ("byte"):
                        dos.writeByte(f.getByte(object));
                        break;
                    case ("short"):
                        dos.writeShort(f.getShort(object));
                        break;
                    case ("int"):
                        dos.writeInt(f.getInt(object));
                        break;
                    case ("boolean"):
                        dos.writeByte(f.getByte(object));
                        break;
                    case ("double"):
                        dos.writeDouble(f.getDouble(object));
                        break;
                    case ("long"):
                        dos.writeLong(f.getLong(object));
                        break;
                }
            }
        }
    }

    /**
     * Метод, десереализующий объект из файла
     * @param file файл для десериализации
     * @return Someobject obj
     * @throws IOException
     */
    public static SomeObject deSerialize(String file) throws IOException {
        SomeObject obj = null;
        try (DataInputStream dis = new DataInputStream(Files.newInputStream(Paths.get(file)))) {
            int number = dis.readInt();
            String string = dis.readUTF();
            boolean someBoolean = dis.readBoolean();
            char character = dis.readChar();
            long someLong = dis.readLong();
            double someDouble = dis.readDouble();
            byte someByte = dis.readByte();
            obj = new SomeObject(number, string, someBoolean, character, someLong, someDouble,someByte);
        }
        return obj;
    }
}
