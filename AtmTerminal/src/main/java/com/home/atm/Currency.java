package com.home.atm;

import java.util.Objects;

public class Currency {

    private String currencyName;

    private int currencyId;

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "currencyName='" + currencyName + '\'' +
                ", currencyId=" + currencyId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return currencyId == currency.currencyId &&
                Objects.equals(currencyName, currency.currencyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyName, currencyId);
    }
}
