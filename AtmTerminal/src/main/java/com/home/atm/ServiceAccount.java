package com.home.atm;

import com.google.common.base.Optional;
import com.home.atm.account.Account;
import com.home.atm.account.AccountDaoImpl;
import org.apache.log4j.Logger;
import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Scanner;

@org.springframework.stereotype.Service("serviceAccount")
public class ServiceAccount {

    private AccountDaoImpl accountDaoImpl;
    private Service service = new Service();
    private static final Logger LOGGER = Logger.getLogger(ServiceAccount.class);

    @Resource(name = "accountDaoImpl")
    public void setAccountDaoImpl(AccountDaoImpl accountDaoImpl) {
        this.accountDaoImpl = accountDaoImpl;
    }

    @Resource(name = "service")
    public void setService(Service service) {
        this.service = service;
    }

    public void launchAccount() throws SQLException {
        System.out.println("Enter account name :");
        Scanner scanner = new Scanner(System.in);
        String account = scanner.next();
        Optional<Account> accountExist = accountDaoImpl.findAccountByName(account);
        if (!accountExist.isPresent()) {
            System.out.println("Account doesn't exist!");
            LOGGER.info("Account doesn't exist!");
        }
        while (accountExist.isPresent()) {
            method(accountExist.get().getAccountId());
        }
    }

    public void method(int accountId) throws SQLException {
        try {
            service.procesInput(accountId);
        } catch (IllegalArgumentException ex) {
            System.out.println("An error has occurred. " + ex.getMessage());
        }
    }
}
