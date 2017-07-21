package com.home.atm.controller;

import com.home.atm.client.controller.RestCurrencyController;
import com.home.atm.currency.Currency;
import com.home.atm.parser.command.BaseIntegrationTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.test.context.ContextConfiguration;
import javax.annotation.Resource;

@ContextConfiguration(locations = "classpath:atm_server.xml")
public class TestRestCurrencyController extends BaseIntegrationTest {

    @Resource(name = "restCurrencyController")
    private RestCurrencyController restCurrencyController;
    private String currencyName = "rub";

    @Before
    public void init() {
        cleanTable("debit", "currency");
    }

    @Rule
    public ExpectedException testRuleException = ExpectedException.none();

    @Test
    public void testAddCurrency() {
        int expectedResult = 1;
        Currency currency = new Currency();
        currency.setName(currencyName);
        int actualResult = restCurrencyController.addCurrency(currency.getName());
        Assert.assertEquals("Actual result must be expected", expectedResult, actualResult);
    }
}
