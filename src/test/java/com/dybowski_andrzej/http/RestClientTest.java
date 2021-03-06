package com.dybowski_andrzej.http;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

class RestClientTest {

    @Test
    void get() {
        RestClient restClient = new RestClient("https://jsonplaceholder.typicode.com/posts/1");
        String data = restClient.get();
        String correctBody = "{\n" +
                "  \"userId\": 1,\n" +
                "  \"id\": 1,\n" +
                "  \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n" +
                "  \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n" +
                "}";

        Assertions.assertEquals(correctBody, data);
    }

    @Test
    void getResponeHeaders() {
        RestClient restClient = new RestClient("https://jsonplaceholder.typicode.com/posts/1");
        String data = restClient.get();

        HttpHeaders headers = restClient.getResponseHeaders();

        Assertions.assertEquals("[292]",headers.get("content-length").toString());
    }
}