package com.home.atm.currency;

import com.google.common.base.Optional;

import java.util.List;

public interface CurrencyDao {

    Optional<Currency> findCurrency(String currencyInput);

    int addCurrency(String currencyName);

    void deleteCurrency(int currencyId);

    List<Currency> getAllCurrency();
}
