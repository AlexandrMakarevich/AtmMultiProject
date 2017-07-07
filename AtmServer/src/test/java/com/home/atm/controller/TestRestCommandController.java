package com.home.atm.controller;

import com.home.atm.client.CommandBean;
import com.home.atm.client.controller.RestCommandController;
import com.home.atm.command.PrintBalance;
import com.home.atm.parser.command.BaseIntegrationTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import javax.annotation.Resource;
import java.util.List;

@ContextConfiguration(locations = "classpath:atm_server.xml")
public class TestRestCommandController extends BaseIntegrationTest {

    @Resource(name = "restCommandController")
    private RestCommandController restCommandController;
    private String accountName = "Jora";
    private String command = "balance";
    private String currencyName = "eur";
    private int balance = 333;

    @Before
    public void init() {
        cleanTable("debit", "account", "currency");
    }

    @Test
    public void testProcessCommand() {
        PrintBalance expectedPrintBalance = new PrintBalance(currencyName, balance);
        int accountId = insert("account", "account_name", accountName);
        int currencyId = insert("currency", "currency_name", currencyName);
        insertBalance(accountId, currencyId, balance);
        CommandBean commandBean = new CommandBean();
        commandBean.setAccountId(accountId);
        commandBean.setCommandName(command);
        List<PrintBalance> balanceList = restCommandController.processCommand(commandBean);
        Assert.assertEquals("Actual result must be expected",
                expectedPrintBalance, balanceList.get(0));
    }
}
