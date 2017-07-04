package com.home.atm;

import com.google.common.base.Optional;

public interface AccountDao {

    Optional<Account> findAccountByName(String accountInput);
}
