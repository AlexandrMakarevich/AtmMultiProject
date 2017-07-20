package com.home.atm.account;

import com.google.common.base.Optional;
import com.home.atm.client.controller.RestAccountController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("restAccountDaoImpl")
public class RestAccountDaoImpl implements AccountDao {

    private RestAccountController restAccountController;

    @Override
    public Optional<Account> findAccountByName(String accountInput) {
        return null;
    }

    @Override
    public int createAccount(String accountName) {
        return 0;
    }

    @Override
    public void deleteAccount(int accountId) {

    }

    @Override
    public List<Account> getAllAccounts() {
       return restAccountController.getAllAccounts();
    }

    @Autowired
    public void setRestAccountController(RestAccountController restAccountController) {
        this.restAccountController = restAccountController;
    }
}
