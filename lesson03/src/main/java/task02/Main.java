package task02;

public class Main {
    public static void main(String[] args) {
        ObjectBox box = new ObjectBox();
        box.addObject(new Object());
        System.out.println(box.dump());
    }
}
