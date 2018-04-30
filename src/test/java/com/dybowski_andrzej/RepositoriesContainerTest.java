package com.dybowski_andrzej;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RepositoriesContainerTest {

    @Test
    void searchLastModifiedRepository() {
        RepositoriesContainer repositories = new RepositoriesContainer();

        repositories.add(new Repository("repository 1", "2018-04-24 11:32:11 ", "yyyy-MM-dd HH:mm:ss "));
        repositories.add(new Repository("repository 2", "2018-04-25 11:32:11 ", "yyyy-MM-dd HH:mm:ss "));
        repositories.add(new Repository("repository 3", "2018-04-23 11:32:11 ", "yyyy-MM-dd HH:mm:ss "));

        Repository lastModifiedRepository = repositories.getLastModifiedRepository();

        Assertions.assertEquals(new Repository("repository 2", "2018-04-25 11:32:11 ", "yyyy-MM-dd HH:mm:ss "), lastModifiedRepository);
    }
}