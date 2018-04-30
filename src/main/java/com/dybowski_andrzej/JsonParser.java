package com.dybowski_andrzej;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonParser {
    private RepositoriesContainer repositories = new RepositoriesContainer();

    public void addRepositoriesFromRawJsonBody(String rawJsonBody) {
        JSONArray jsonArray = new JSONArray(rawJsonBody);

        for(Object object : jsonArray) {
            JSONObject jsonObject = new JSONObject(object.toString());

            String date = jsonObject.getString("updated_at");
            date = date.replace("T", "");
            date = date.replace("Z", "");
            String pattern = "yyyy-MM-ddHH:mm:SS";

            repositories.add(new Repository(jsonObject.getString("name"), date, pattern));
        }
    }

    public RepositoriesContainer getRepositories() {
        return repositories;
    }
}
