package com.home.atm;

public interface CurrencyDao {

    void insertCurrency(String currencyName);

    Currency findCurrencyByName(String currencyNAme);
}
