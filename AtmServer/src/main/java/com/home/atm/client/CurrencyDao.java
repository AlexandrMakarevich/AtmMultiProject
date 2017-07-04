package com.home.atm.client;

import com.google.common.base.Optional;

public interface CurrencyDao {

    Optional<Currency> findCurrency(String currencyInput);

    int addCurrency(String currencyName);
}
