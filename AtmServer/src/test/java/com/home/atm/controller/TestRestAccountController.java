package com.home.atm.controller;

import com.home.atm.account.Account;
import com.home.atm.client.controller.RestAccountController;
import com.home.atm.parser.command.BaseIntegrationTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.test.context.ContextConfiguration;
import javax.annotation.Resource;

@ContextConfiguration(locations = "classpath:atm_server.xml")
public class TestRestAccountController extends BaseIntegrationTest {

    @Resource(name = "restAccountController")
    private RestAccountController restAccountController;
    private String accountName = "Petya";

    @Before
    public void init() {
       cleanTable("debit", "account");
    }

    @Rule
    public ExpectedException testRuleException = ExpectedException.none();

    @Test
    public void testGetAccountByName() {
        insert("account", "account_name", accountName);
        Account actualAccount = restAccountController.getAccountByName(accountName);
        Assert.assertEquals("Actual accountName must be the same as accountName",
                accountName, actualAccount.getAccountName());
    }

    @Test
    public void testWhenAccountNotExist() {
        String accountName = "jora";
        this.testRuleException.expect(IllegalStateException.class);
        this.testRuleException.expectMessage("No column was changed!");
        restAccountController.getAccountByName(accountName);
    }

    @Test
    public void testCreateAccount() {
        int expectedResult = 1;
        Account account = new Account();
        account.setAccountName(accountName);
        int actualResult = restAccountController.createAccount(account.getAccountName());
        Assert.assertEquals("Actual result must be expected", expectedResult, actualResult);
    }
}
