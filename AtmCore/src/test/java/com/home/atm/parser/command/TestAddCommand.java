package com.home.atm.parser.command;

import com.home.atm.command.AddCommand;
import com.home.atm.command.PrintBalance;
import com.home.atm.exception.AtmException;
import com.home.atm.exception.ErrorCodes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

public class TestAddCommand extends BaseIntegrationTest {

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private AddCommand addCommand;
    private String currencyName = "usd";
    private String accountName = "ivanov";
    private int balance = 543;

    @Rule
    public ExpectedException testRuleException = ExpectedException.none();

    @Before
    public void init() {
        cleanTable("debit", "account", "currency");
        addCommand = new AddCommand(namedParameterJdbcTemplate, currencyName, balance);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testCheckAddMoneyOnBalance() {
        int accountId = insert("account", "account_name", accountName);
        insert("currency", "currency_name", currencyName);
        List<PrintBalance> addList = addCommand.executeDb(accountId);
        int actualBalance = addList.get(0).getBalance();
        Assert.assertEquals("ActualBalance must be the same as balance", balance, actualBalance);
    }

    @Test
    public void testCaseWhenInsertNotHappen() {
        int accountId = insert("account", "account_name", accountName);
        this.testRuleException.expect(AtmException.class);
        this.testRuleException.expect(new AtmExceptionMatcher(new AtmException(ErrorCodes.NO_CURRENCY)));
        addCommand.executeDb(accountId);
    }
}
