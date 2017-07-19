package com.home.atm.account;

import com.google.common.base.Optional;
import java.util.List;

public interface AccountDao {

    Optional<Account> findAccountByName(String accountInput);

    int createAccount(String accountName);

    void deleteAccount(int accountId);

    List<Account> getAllAccounts();
}
