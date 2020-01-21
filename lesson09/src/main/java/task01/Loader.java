package task01;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Loader extends ClassLoader {
    private String byteCodePath;

    public Loader(String byteCodePath) {
        this.byteCodePath = byteCodePath;
    }

    @Override
    public Class<?> loadClass(String s) throws ClassNotFoundException {
        if (s.equalsIgnoreCase("SomeClass")) return findClass(s);
        return super.loadClass(s);
    }

    @Override
    protected Class<?> findClass(String s) throws ClassNotFoundException {
        if (s.equalsIgnoreCase("SomeClass")) {
            try {
                byte[] bytes = Files.readAllBytes(Paths.get(byteCodePath));
                return defineClass(s, bytes, 0, bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.findClass(s);
    }
}
