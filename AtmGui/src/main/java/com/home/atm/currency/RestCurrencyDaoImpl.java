package com.home.atm.currency;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.atm.BaseRestDaoImpl;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.List;

@Component("restCurrencyDaoImpl")
public class RestCurrencyDaoImpl extends BaseRestDaoImpl implements CurrencyDao {

    @Override
    public int addCurrency(String currencyName) {
        restTemplate.postForObject(baseUrl + "/AtmServer/createCurrency", currencyName, String.class);
        return 1;
    }

    @Override
    public void deleteCurrency(int currencyId) {
        restTemplate.postForObject(baseUrl + "/AtmServer/deleteCurrency", currencyId, Integer.class);
    }

    @Override
    public List<Currency> getAllCurrency() {
        ObjectMapper objectMapper = new ObjectMapper();
        String currencyList = restTemplate.getForObject(baseUrl + "/AtmServer/getAllCurrency", String.class);
        try {
            return objectMapper.readValue(currencyList, new TypeReference<List<Currency>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
