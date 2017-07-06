package com.home.atm.client.controller;

import com.google.common.base.Optional;
import com.home.atm.client.Currency;
import com.home.atm.client.CurrencyDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@Transactional
@RequestMapping("/currency")
public class RestCurrencyController {

    @Resource(name = "currencyDaoImpl")
    private CurrencyDao currencyDao;

    @RequestMapping(method = RequestMethod.GET)
    public Currency getCurrencyByName(@RequestParam(value = "currencyName") String currencyName) {
        Optional<Currency> currency = currencyDao.findCurrency(currencyName);
        if (!currency.isPresent()) {
            throw new IllegalStateException("Currency doesn't exist");
        }
        return currency.get();
    }

    @RequestMapping(method = RequestMethod.POST)
    public int addCurrency(@RequestBody Currency currency) {
        return currencyDao.addCurrency(currency.getName());
    }
}
