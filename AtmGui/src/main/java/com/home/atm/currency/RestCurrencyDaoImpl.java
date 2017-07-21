package com.home.atm.currency;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.List;

@Component("restCurrencyDaoImpl")
public class RestCurrencyDaoImpl implements CurrencyDao {

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public Optional<Currency> findCurrency(String currencyInput) {
        throw new IllegalArgumentException("Not use");
    }

    @Override
    public int addCurrency(String currencyName) {
        restTemplate.postForObject("http://localhost:8080/AtmServer/createCurrency", currencyName, String.class);
        return 1;
    }

    @Override
    public void deleteCurrency(int currencyId) {
        restTemplate.postForObject("http://localhost:8080/AtmServer/deleteCurrency", currencyId, Integer.class);
    }

    @Override
    public List<Currency> getAllCurrency() {
        ObjectMapper objectMapper = new ObjectMapper();
        String currencyList = restTemplate.getForObject("http://localhost:8080/AtmServer/getAllCurrency", String.class);
        try {
            return objectMapper.readValue(currencyList, new TypeReference<List<Currency>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
