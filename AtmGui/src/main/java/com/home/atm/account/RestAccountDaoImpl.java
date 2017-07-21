package com.home.atm.account;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import com.home.atm.BaseRestDaoImpl;
import org.springframework.stereotype.Repository;
import java.io.IOException;
import java.util.List;

@Repository("restAccountDaoImpl")
public class RestAccountDaoImpl extends BaseRestDaoImpl implements AccountDao {

    @Override
    public Optional<Account> findAccountByName(String accountInput) {
        throw new IllegalArgumentException("Not used");
    }

    @Override
    public int createAccount(String accountName) {
        restTemplate.postForObject(baseUrl + "/AtmServer/createAccount", accountName, String.class);
        return 1;
    }

    @Override
    public void deleteAccount(int accountId) {
        restTemplate.postForObject(baseUrl + "/AtmServer/deleteAccount", accountId, Integer.class);
    }

    @Override
    public List<Account> getAllAccounts()  {
        ObjectMapper objectMapper = new ObjectMapper();
      String accountList = restTemplate.getForObject(baseUrl + "/AtmServer/getAllAccounts", String.class);
        try {
            return objectMapper.readValue(accountList, new TypeReference<List<Account>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
