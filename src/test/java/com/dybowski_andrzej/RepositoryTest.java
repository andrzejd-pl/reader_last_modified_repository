package com.dybowski_andrzej;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

    @Test
    void getLastModifiedDate() {
        String date = "2018-04-24T11:32:11Z";
        date = date.replace("T", " ");
        date = date.replace("Z", " ");

        String name = "GitHub repository";

        String pattern = "yyyy-MM-dd HH:mm:SS ";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate correctDate = LocalDate.parse(date, formatter);

        Repository repository = new Repository(name, date, pattern);

        LocalDate dateRepository = repository.getLastModifiedDate();


        Assertions.assertTrue(dateRepository.isEqual(correctDate));
    }

    @Test
    void getName() {
        String date = "2018-04-24T11:32:11Z";
        date = date.replace("T", " ");
        date = date.replace("Z", " ");

        String name = "GitHub repository";
        String pattern = "yyyy-MM-dd HH:mm:SS ";

        Repository repository = new Repository(name, date, pattern);

        Assertions.assertEquals(name, repository.getName());
    }
}