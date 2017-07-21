package com.home.atm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public class BaseRestDaoImpl {

    protected RestTemplate restTemplate = new RestTemplate();
    protected String baseUrl;

    @Value("${baseUrl}")
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
