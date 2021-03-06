package com.dybowski_andrzej.json;

import com.dybowski_andrzej.repository.RepositoriesContainer;
import com.dybowski_andrzej.repository.Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JsonParserTest {

    @Test
    void addRepositoriesFromRawJsonBodyAndGetRepositories() {
        String[] rawData = {
                "[{" +
                        "\"name\":\"repo1\"," +
                        "\"pushed_at\":\"2018-04-24T21:54:35Z\"" +
                        "},{" +
                        "\"name\":\"repo2\"," +
                        "\"pushed_at\":\"2018-04-24T21:54:36Z\"" +
                        "},{" +
                        "\"name\":\"repo3\"," +
                        "\"pushed_at\":\"2018-04-24T21:54:37Z\"" +
                        "}" +
                        "]",
                "[{" +
                        "\"name\":\"repo4\"," +
                        "\"pushed_at\":\"2018-04-24T21:54:34Z\"" +
                        "},{" +
                        "\"name\":\"repo5\"," +
                        "\"pushed_at\":\"2018-04-24T21:54:33Z\"" +
                        "},{" +
                        "\"name\":\"repo6\"," +
                        "\"pushed_at\":\"2018-04-24T21:54:38Z\"" +
                        "}" +
                        "]"
        };

        Repository repository = (new Repository.Builder().addName("repo6").addDateTime("2018-04-24 21:54:38 ", "yyyy-MM-dd HH:mm:ss ").build());

        JsonParser parser = new JsonParser();

        parser.addRepositoriesFromRawJsonBody(rawData[0]);
        parser.addRepositoriesFromRawJsonBody(rawData[1]);

        RepositoriesContainer repositories = parser.getRepositories();

        Assertions.assertEquals(repository, repositories.getLastModifiedRepository());
    }
}