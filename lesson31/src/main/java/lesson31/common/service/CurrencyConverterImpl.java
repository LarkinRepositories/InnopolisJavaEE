package lesson31.common.service;

import lesson31.common.CurrencyConverter;
import lesson31.common.Money;

public class CurrencyConverterImpl implements CurrencyConverter {
    @Override
    public double convert(double sum) {
        return sum/60;
    }

    @Override
    public Money convertMoney(Money money) {
        money.setCurrencyName("USD");
        money.setSum(convert(money.getSum()));
        return money;
    }
}
