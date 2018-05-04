package com.dybowski_andrzej.http;

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
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
    }

    public String get() {
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        ResponseEntity<String> responseEntity = rest.exchange(address, HttpMethod.GET, requestEntity, String.class);
        this.responseHeaders = responseEntity.getHeaders();
        return responseEntity.getBody();
    }

    public HttpHeaders getResponseHeaders() {
        return responseHeaders;
    }
}
