package task01;

public class Main {
    public static void main(String[] args) {
        Number[] numbers = {1,10,15};
        Number[] numbers1 = {59,16,25,190};
        MathBox box = new MathBox(numbers);
        System.out.println(box.summator());
        System.out.println(box);
        MathBox box1 = new MathBox(numbers1);
        System.out.println(box.equals(box1) + " | " +  box1.equals(box));
        System.out.println(box.hashCode());
        System.out.println(box1.hashCode());
        System.out.println(box.splitter(2));
        System.out.println(box);
    }
}
