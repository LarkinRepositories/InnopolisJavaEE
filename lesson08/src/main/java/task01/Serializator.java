package task01;

import lombok.Getter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
        List<Field> objectFields = new ArrayList<>(Arrays.asList(object.getClass().getFields()));
        try (DataOutputStream dos = new DataOutputStream(Files.newOutputStream(Paths.get(file)))) {
            for (Field f: objectFields) {
                if ("long".equalsIgnoreCase(String.valueOf(f.getType()))) {
                    dos.writeLong(f.getLong(f));
                } else if ("int".equalsIgnoreCase(String.valueOf(f.getType()))) {
                    dos.writeInt(f.getInt(f));
                } else if ("String".equalsIgnoreCase(String.valueOf(f.getType()))) {
                    dos.writeUTF(f.toString());
                } else if ("boolean".equalsIgnoreCase(String.valueOf(f.getType()))) {
                    dos.writeBoolean(f.getBoolean(f));
                } else if ("double".equalsIgnoreCase(String.valueOf(f.getType()))) {
                    dos.writeDouble(f.getDouble(f));
                } else if ("char".equalsIgnoreCase(String.valueOf(f.getType()))) {
                    dos.writeChar(f.getChar(f));
                } else if ("byte".equalsIgnoreCase(String.valueOf(f.getType()))) {
                    dos.writeByte(f.getByte(f));
                }

            }
        }
    }

    public static Object deSerialize(String file) throws IOException {
        try (DataInputStream dis = new DataInputStream(Files.newInputStream(Paths.get(file)))) {
            return null;
        }
    }
}
