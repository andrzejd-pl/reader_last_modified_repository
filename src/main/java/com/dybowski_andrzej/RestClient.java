package com.dybowski_andrzej;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class RestClient {
    private String server;
    private RestTemplate rest;
    private HttpHeaders headers;
    private HttpStatus status;
    private HttpHeaders responseHeaders;

    public RestClient(String server) {
        this.server = server;
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
    }

    public String get(String uri) {
        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        ResponseEntity<String> responseEntity = rest.exchange(server + uri, HttpMethod.GET, requestEntity, String.class);
        this.status = responseEntity.getStatusCode();
        this.responseHeaders = responseEntity.getHeaders();
        return responseEntity.getBody();
    }

    public HttpHeaders getResponseHeaders() {
        return responseHeaders;
    }
}
