package com.home.atm.controller;

import com.home.atm.client.CommandBean;
import com.home.atm.client.controller.CommandController;
import com.home.atm.command.PrintBalance;
import com.home.atm.exception.ErrorCodes;
import com.home.atm.parser.command.BaseIntegrationTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.ModelMap;
import org.springframework.validation.MapBindingResult;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@ContextConfiguration(locations = "classpath:atm_server.xml")
public class TestCommandController extends BaseIntegrationTest {

    @Resource(name = "commandController")
    private CommandController commandController;
    private String currencyName = "rub";
    private int balance = 200;
    private String accountName = "Vova";
    private CommandBean commandBean;
    private MapBindingResult result;

    @Before
    public void init() {
        cleanTable("debit", "account", "currency");
        result = new MapBindingResult(new HashMap(), "aa");
        int accountId = insert("account", "account_name", accountName);
        int currencyId = insert("currency", "currency_name", currencyName);
        insertBalance(accountId, currencyId, balance);
        commandBean = new CommandBean();
        commandBean.setAccountId(accountId);
        commandBean.setAccountName(accountName);
    }

    @Test
    public void checkProcessCommand() {
        ModelMap modelMap = new ModelMap();
        String commandName = "balance";
        commandBean.setCommandName(commandName);
        String expectedResult = "balance";
        PrintBalance expectedObject = new PrintBalance(currencyName, balance);
        String actualResult = commandController.processCommand(commandBean, result, modelMap);
        List<PrintBalance> resultList = (List<PrintBalance>) modelMap.get("operationResult");
        PrintBalance actualObject = resultList.get(0);
        Assert.assertEquals("Actual object must be expected", expectedObject, actualObject);
        Assert.assertEquals("Actual pageName must be expectedPageName", expectedResult, actualResult);
    }

    @Test
    public void checkExceptionNotEnoughMoney() {
        String commandName = "- rub 1000";
        String expectedMessage = "Not enough money on the account!";
        String actualMessage = getErrorMessage(commandName);
        Assert.assertEquals("Actual message must be expected", expectedMessage, actualMessage);
    }

    @Test
    public void checkExceptionNoCurrency() {
        String commandName = "- eur 100";
        String expectedMessage = "You don't have money on this currency!";
        String actualMessage = getErrorMessage(commandName);
        Assert.assertEquals("Actual message must be expected", expectedMessage, actualMessage);
    }

    @Test
    public void checkExceptionNoChanges() {
        String commandName = "+ xxx 100";
        String expectedMessage = "You don't have money on currency";
        String actualMessage = getErrorMessage(commandName);
        Assert.assertEquals("Actual message must be expected", expectedMessage, actualMessage);
    }

    public String getErrorMessage(String commandName) {
        ModelMap modelMap = new ModelMap();
        commandBean.setCommandName(commandName);
        commandController.processCommand(commandBean, result, modelMap);
        ErrorCodes actualResult = (ErrorCodes) modelMap.get("errorCode");
        return actualResult.getErrorMessage();
    }
}
