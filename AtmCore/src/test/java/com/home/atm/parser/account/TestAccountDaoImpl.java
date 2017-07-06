package com.home.atm.parser.account;

import com.google.common.base.Optional;
import com.home.atm.account.Account;
import com.home.atm.account.AccountDao;
import com.home.atm.parser.command.BaseIntegrationTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import javax.annotation.Resource;

public class TestAccountDaoImpl extends BaseIntegrationTest {

    @Resource(name = "accountDaoImpl")
    private AccountDao accountDao;
    private String accountName = "Vasya";

    @Before
    public void init() {
        cleanTable("debit", "account");
    }

    @Test
    public void testFindAndCreateAccount() {
        accountDao.createAccount(accountName);
        Optional<Account> actualAccount = accountDao.findAccountByName(accountName);
        Assert.assertEquals("Account name must be the same as actualAccountName",
                accountName, actualAccount.get().getAccountName());
    }

    @Test
    public void testWhenAccountNotExist() {
        Optional<Account> account = accountDao.findAccountByName(accountName);
        Assert.assertFalse(account.isPresent());
    }
}
