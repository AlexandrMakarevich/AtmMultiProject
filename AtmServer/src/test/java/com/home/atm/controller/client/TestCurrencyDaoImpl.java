package com.home.atm.controller.client;

import com.google.common.base.Optional;
import com.home.atm.currency.Currency;
import com.home.atm.currency.CurrencyDao;
import com.home.atm.parser.command.BaseIntegrationTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import javax.annotation.Resource;

@ContextConfiguration(locations = "classpath:atm_server.xml")
public class TestCurrencyDaoImpl extends BaseIntegrationTest{

    @Resource(name = "currencyDaoImpl")
    private CurrencyDao currencyDao;

    @Before
    public void init() {
        cleanTable("debit" ,"currency");
    }

    @Test
    public void testFindAndCreateCurrency() {
        String currencyName = "rabbit";
        currencyDao.addCurrency(currencyName);
        Optional<Currency> actualCurrency = currencyDao.findCurrency(currencyName);
        Assert.assertEquals("Actual result must be expected", currencyName, actualCurrency.get().getName());
    }

    @Test
    public void testWhenCurrencyNotExist() {
        String currencyName = "eur";
        Optional<Currency> actualCurrency = currencyDao.findCurrency(currencyName);
        Assert.assertFalse(actualCurrency.isPresent());
    }
}
