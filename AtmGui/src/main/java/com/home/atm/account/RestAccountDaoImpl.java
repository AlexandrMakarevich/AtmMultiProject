package com.home.atm.account;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.List;

@Repository("restAccountDaoImpl")
public class RestAccountDaoImpl implements AccountDao {

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public Optional<Account> findAccountByName(String accountInput) {
        throw new IllegalArgumentException("Not used");
    }

    @Override
    public int createAccount(String accountName) {
        restTemplate.postForObject("http://localhost:8080/AtmServer/createAccount", accountName, String.class);
        return 1;
    }

    @Override
    public void deleteAccount(int accountId) {
        restTemplate.postForObject("http://localhost:8080/AtmServer/deleteAccount", accountId, Integer.class);
    }

    @Override
    public List<Account> getAllAccounts()  {
        ObjectMapper objectMapper = new ObjectMapper();
      String accountList = restTemplate.getForObject("http://localhost:8080/AtmServer/getAllAccounts", String.class);
        try {
            return objectMapper.readValue(accountList, new TypeReference<List<Account>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
