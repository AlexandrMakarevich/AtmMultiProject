package com.home.atm.client.controller;

import com.google.common.base.Optional;
import com.home.atm.currency.Currency;
import com.home.atm.currency.CurrencyDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController("restCurrencyController")
@Transactional
public class RestCurrencyController {

    @Resource(name = "currencyDaoImpl")
    private CurrencyDao currencyDao;

    @RequestMapping(value = "/getAllCurrency", method = RequestMethod.GET)
    public List<Currency> getAllCurrency() {
        return currencyDao.getAllCurrency();
    }

    @RequestMapping(value = "/deleteCurrency", method = RequestMethod.POST)
    public void deleteCurrency(@RequestBody int currencyId) {
        currencyDao.deleteCurrency(currencyId);
    }

    @RequestMapping(value = "/createCurrency", method = RequestMethod.POST)
    public int addCurrency(@RequestBody String currencyName) {
        return currencyDao.addCurrency(currencyName);
    }
}
