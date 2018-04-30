package com.dybowski_andrzej;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestClient {
    private String address;
    private RestTemplate rest;
    private HttpHeaders headers;
    private HttpHeaders responseHeaders;

    public RestClient(String address) {
        this.address = address;
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
    }

    public String get() {
        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        ResponseEntity<String> responseEntity = rest.exchange(address, HttpMethod.GET, requestEntity, String.class);
        this.responseHeaders = responseEntity.getHeaders();
        return responseEntity.getBody();
    }

    public HttpHeaders getResponseHeaders() {
        return responseHeaders;
    }
}
