package com.dybowski_andrzej;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JsonParserTest {

    @Test
    void addRepositoriesFromRawJsonBodyAndGetRepositories() {
        String[] rawData = {
                "[{" +
                        "\"name\":\"repo1\"," +
                        "\"updated_at\":\"2018-04-24T21:54:35Z\"" +
                        "},{" +
                        "\"name\":\"repo2\"," +
                        "\"updated_at\":\"2018-04-24T21:54:36Z\"" +
                        "},{" +
                        "\"name\":\"repo3\"," +
                        "\"updated_at\":\"2018-04-24T21:54:37Z\"" +
                        "}" +
                        "]",
                "[{" +
                        "\"name\":\"repo4\"," +
                        "\"updated_at\":\"2018-04-24T21:54:34Z\"" +
                        "},{" +
                        "\"name\":\"repo5\"," +
                        "\"updated_at\":\"2018-04-24T21:54:33Z\"" +
                        "},{" +
                        "\"name\":\"repo6\"," +
                        "\"updated_at\":\"2018-04-24T21:54:38Z\"" +
                        "}" +
                        "]"
        };
        Repository repository = new Repository("repo6", "2018-04-24 21:54:38 ", "yyyy-MM-dd HH:mm:ss ");

        JsonParser parser = new JsonParser();

        parser.addRepositoriesFromRawJsonBody(rawData[0]);
        parser.addRepositoriesFromRawJsonBody(rawData[1]);

        RepositoriesContainer repositories = parser.getRepositories();

        Assertions.assertEquals(repository, repositories.getLastModifiedRepository());
    }
}