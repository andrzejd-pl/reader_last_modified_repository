package com.dybowski_andrzej;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class RepositoryTest {

    @Test
    void getLastModifiedDate() {
        String firstDate = "2018-04-24T11:32:11Z";
        firstDate = firstDate.replace("T", "");
        firstDate = firstDate.replace("Z", "");

        String name = "GitHub repository";

        String pattern = "yyyy-MM-ddHH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime correctDate = LocalDateTime.parse(firstDate, formatter);

        Repository repository = new Repository(name, correctDate);

        LocalDateTime dateRepository = repository.getLastModifiedDate();


        Assertions.assertTrue(dateRepository.isEqual(correctDate));
    }

    @Test
    void getName() {
        String date = "2018-04-24T11:32:11Z";
        date = date.replace("T", " ");
        date = date.replace("Z", " ");

        String name = "GitHub repository";
        String pattern = "yyyy-MM-dd HH:mm:ss ";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

        Repository repository = new Repository(name, dateTime);

        Assertions.assertEquals(name, repository.getName());
    }


    @Test
    void equals() {
        String date = "2018-04-24T11:32:11Z";
        String date2 = "2018-04-24T11:32:12Z";
        date = date.replace("T", " ");
        date = date.replace("Z", " ");

        date2 = date2.replace("T", " ");
        date2 = date2.replace("Z", " ");

        String name = "GitHub repository";
        String pattern = "yyyy-MM-dd HH:mm:ss ";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(date2, formatter);

        Repository repository = new Repository.Builder().addName(name).addDateTime(date, pattern).build();

        Assertions.assertEquals(new Repository(name, dateTime), repository);
        Assertions.assertNotEquals(new Repository(name, dateTime2), repository);
    }
}