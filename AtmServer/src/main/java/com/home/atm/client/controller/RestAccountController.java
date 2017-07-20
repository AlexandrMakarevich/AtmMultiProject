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

    @RequestMapping(value = "/restAccount/{accountName}", method = RequestMethod.GET)
    public Account getAccountByName(@PathVariable("accountName") String accountName) {
        Optional<Account> account = accountDao.findAccountByName(accountName);
        if (!account.isPresent()) {
            throw new IllegalStateException("No column was changed!");
        }
        return account.get();
    }

    @RequestMapping(value = "/restAccount1", method = RequestMethod.GET)
    public List<Account> getAllAccounts() {
        return accountDao.getAllAccounts();
    }

    @RequestMapping(value = "/account1", method = RequestMethod.POST)
    public int createAccount(@RequestBody Account account) {
        return accountDao.createAccount(account.getAccountName());
    }
}
