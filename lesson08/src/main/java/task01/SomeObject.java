package task01;

import lombok.Data;

import java.io.Serializable;


@Data
public class SomeObject implements Serializable {
    private int number;
    private String string;
    private boolean someBoolean;
    private char character;
    private long someLong;
    private double someDouble;
    private byte someByte;


    public SomeObject(int number, String string, boolean someBoolean, char character, long someLong, double someDouble, byte someByte) {
        this.number = number;
        this.string = string;
        this.someBoolean = someBoolean;
        this.character = character;
        this.someLong = someLong;
        this.someDouble = someDouble;
        this.someByte = someByte;
    }
}
