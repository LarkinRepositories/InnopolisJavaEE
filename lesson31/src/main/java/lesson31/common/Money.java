package lesson31.common;


import java.io.Serializable;

public class Money implements Serializable {
    private double sum = 0;
    private String currencyName;

    public Money(double sum, String currencyName) {
        this.sum = sum;
        this.currencyName = currencyName;
    }

    public Money() {}

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }
}
