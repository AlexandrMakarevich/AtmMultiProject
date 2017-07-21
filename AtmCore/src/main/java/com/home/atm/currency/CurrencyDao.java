package com.home.atm.currency;

import java.util.List;

public interface CurrencyDao {

    int addCurrency(String currencyName);

    void deleteCurrency(int currencyId);

    List<Currency> getAllCurrency();
}
