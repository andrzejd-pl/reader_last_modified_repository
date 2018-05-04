package com.dybowski_andrzej.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RepositoriesContainerTest {

    @Test
    void searchLastModifiedRepository() {
        RepositoriesContainer repositories = new RepositoriesContainer();

        repositories.add(new Repository.Builder().addName("repository 1").addDateTime( "2018-04-24 11:32:11 ", "yyyy-MM-dd HH:mm:ss ").build());
        repositories.add(new Repository.Builder().addName("repository 2").addDateTime( "2018-04-25 11:32:11 ", "yyyy-MM-dd HH:mm:ss ").build());
        repositories.add(new Repository.Builder().addName("repository 3").addDateTime( "2018-04-23 11:32:11 ", "yyyy-MM-dd HH:mm:ss ").build());

        Repository lastModifiedRepository = repositories.getLastModifiedRepository();

        Assertions.assertEquals(new Repository.Builder().addName("repository 2").addDateTime( "2018-04-25 11:32:11 ", "yyyy-MM-dd HH:mm:ss ").build(), lastModifiedRepository);
    }
}