package com.dybowski_andrzej.json;

import com.dybowski_andrzej.repository.RepositoriesContainer;
import com.dybowski_andrzej.repository.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonParser {
    private RepositoriesContainer repositories = new RepositoriesContainer();

    public void addRepositoriesFromRawJsonBody(String rawJsonBody) {
        JSONArray jsonArray = new JSONArray(rawJsonBody);

        for (Object object : jsonArray) {
            JSONObject jsonObject = new JSONObject(object.toString());

            String pushed_at = jsonObject.getString("pushed_at");
            pushed_at = pushed_at.replace("T", "");
            pushed_at = pushed_at.replace("Z", "");
            String pattern = "yyyy-MM-ddHH:mm:ss";

            repositories.add(new Repository.Builder().addName(jsonObject.getString("name")).addDateTime(pushed_at, pattern).build());
        }
    }

    public RepositoriesContainer getRepositories() {
        return repositories;
    }
}
