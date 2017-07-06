package com.home.atm.controller;

import com.home.atm.account.Account;
import com.home.atm.client.controller.AccountController;
import com.home.atm.parser.command.BaseIntegrationTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.validation.MapBindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import javax.annotation.Resource;
import java.util.HashMap;

@ContextConfiguration(locations = "classpath:atm_server.xml")
public class TestAccountController extends BaseIntegrationTest {

    @Resource(name = "accountController")
    private AccountController accountController;
    private MapBindingResult result;
    private Account account;
    private String accountName = "Petya";

    @Before
    public void init() {
        result = new MapBindingResult(new HashMap(), "aa");
        account = new Account();
        account.setAccountName(accountName);
    }

    @Test
    public void checkWrongAccount() {
        String expectedPageName = "accountJsp";
        cleanTable("account");
        result.rejectValue("accountName", "account_not_found");
        ModelAndView actualPageName = accountController.checkAccount(account, result, new RedirectAttributesModelMap());
        Assert.assertEquals("ActualPageName must be expectedPageName ", expectedPageName, actualPageName.getViewName());
    }

    @Test
    public void checkExistAccount() {
        String expectedPageName = "redirect:/command";
        cleanTable("account");
        insert("account", "account_name", accountName);
        ModelAndView actualPageName = accountController.checkAccount(account, result, new RedirectAttributesModelMap());
        Assert.assertEquals("ActualPageName must be expectedPageName ", expectedPageName, actualPageName.getViewName());
    }
}
