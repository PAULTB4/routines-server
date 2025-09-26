package com.paul.routines;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Component
public class ClientsApi {
    private final RestClient rest;

    public ClientsApi(@Value("${clients.base-url}") String baseUrl) {
        this.rest = RestClient.builder().baseUrl(baseUrl).build();
    }

    public boolean clientExists(Long id) {
        try {
            rest.get().uri("/clients/{id}", id).retrieve().toBodilessEntity();
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }
}
