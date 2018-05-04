package com.dybowski_andrzej;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JsonParser {
    private RepositoriesContainer repositories = new RepositoriesContainer();

    public void addRepositoriesFromRawJsonBody(String rawJsonBody) {
        JSONArray jsonArray = new JSONArray(rawJsonBody);

        for (Object object : jsonArray) {
            JSONObject jsonObject = new JSONObject(object.toString());

            String updated_at = jsonObject.getString("updated_at");
            String pushed_at = jsonObject.getString("pushed_at");
            updated_at = updated_at.replace("T", "");
            updated_at = updated_at.replace("Z", "");
            pushed_at = pushed_at.replace("T", "");
            pushed_at = pushed_at.replace("Z", "");
            String pattern = "yyyy-MM-ddHH:mm:ss";

            repositories.add(new Repository(jsonObject.getString("name"), this.returnLastDate(pushed_at, updated_at, pattern)));
        }
    }

    public RepositoriesContainer getRepositories() {
        return repositories;
    }

    private LocalDateTime returnLastDate(String firstDateTime, String secondDateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime first = LocalDateTime.parse(firstDateTime, formatter);
        LocalDateTime second = LocalDateTime.parse(secondDateTime, formatter);

        return (first.isAfter(second)) ? (first) : (second);
    }
}
