package com.home.atm.parser.command;

import com.home.atm.command.Command;
import com.home.atm.command.PrintBalance;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TestPrintBalanceCommand extends BaseIntegrationTest {

    @Resource(name = "printBalanceCommand")
    private Command command;

    @Before
    public void init() {
        cleanTable("debit", "currency", "account");
    }

    @Test
    @Rollback(false)
    @Transactional
    public void test() throws SQLException, IOException {
        String currencyName = "rub";
        String accountName = "petrov";
        int balance = 130;
        int currencyId = insert("currency", "currency_name", currencyName);
        int accountId = insert("account", "account_name", accountName);
        insertBalance(accountId, currencyId, balance);
        PrintBalance expectedBalances = new PrintBalance(currencyName, balance);
        List<PrintBalance> listBalances = command.executeDb(accountId);
        PrintBalance actualBalances = listBalances.get(0);
        Assert.assertEquals("Actual result must be expected ", expectedBalances, actualBalances);
    }
}
