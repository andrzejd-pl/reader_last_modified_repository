package com.dybowski_andrzej;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Repository {

    private LocalDateTime lastModifiedDate;
    private String name;

    public Repository(String name, String dateTime, String pattern) {
        this.name = name;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        this.lastModifiedDate = LocalDateTime.parse(dateTime, formatter);
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Repository) {
            Repository repository = (Repository) obj;

            return repository.name.equals(this.name) && repository.lastModifiedDate.isEqual(this.lastModifiedDate);
        }
        return false;
    }
}
