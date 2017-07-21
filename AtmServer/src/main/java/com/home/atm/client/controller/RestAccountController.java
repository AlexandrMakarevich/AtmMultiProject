package com.home.atm.client.controller;

import com.google.common.base.Optional;
import com.home.atm.account.Account;
import com.home.atm.account.AccountDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@Transactional
@RestController("restAccountController")
public class RestAccountController {

    @Resource(name = "accountDaoImpl")
    private AccountDao accountDao;

    @RequestMapping(value = "/account/{accountName}", method = RequestMethod.GET)
    public Account getAccountByName(@PathVariable("accountName") String accountName) {
        Optional<Account> account = accountDao.findAccountByName(accountName);
        if (!account.isPresent()) {
            throw new IllegalStateException("Account doesn't exist!");
        }
        return account.get();
    }

    @RequestMapping(value = "/getAllAccounts", method = RequestMethod.GET)
    public List<Account> getAllAccounts() {
        return accountDao.getAllAccounts();
    }

    @RequestMapping(value = "/deleteAccount", method = RequestMethod.POST)
    public void deleteAccount(@RequestBody int accountId) {
        accountDao.deleteAccount(accountId);
    }

    @RequestMapping(value = "/createAccount", method = RequestMethod.POST)
    public int createAccount(@RequestBody String accountName) {
        return accountDao.createAccount(accountName);
    }
}
