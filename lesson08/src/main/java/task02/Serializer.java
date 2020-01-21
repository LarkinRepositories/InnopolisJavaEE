package task02;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
Предусмотреть работу c любыми типами полей (полями могут быть ссылочные типы).
 */

public class Serializer {


    /**
     * Метод сериализует объект object в файл file
     * @param object объект сериализации
     * @param file путь к файлу
     */
    public static void serialize(Object object, String file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(file)))) {
            oos.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод десериализует объект из файла file
     * @param file путь к файлу
     * @return объект
     */
    public static Object deSerialize(String file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(Paths.get(file)))) {
            return objectInputStream.readObject();
        }
    }
}
