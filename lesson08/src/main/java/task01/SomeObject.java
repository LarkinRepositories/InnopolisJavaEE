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



}
