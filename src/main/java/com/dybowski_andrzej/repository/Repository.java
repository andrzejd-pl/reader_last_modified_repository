package com.dybowski_andrzej.repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Repository {

    private LocalDateTime lastModifiedDate;
    private String name;

    public Repository(String name, LocalDateTime dateTime) {
        this.name = name;
        this.lastModifiedDate = dateTime;
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

    public static class Builder {
        private LocalDateTime lastModifiedDate;
        private String name;

        public Builder addDateTime(String date, String pattern) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            this.lastModifiedDate = LocalDateTime.parse(date, formatter);
            return this;
        }

        public Builder addName(String name) {
            this.name = name;
            return this;
        }

        public Repository build() {
            if(lastModifiedDate == null || name == null) throw new NullPointerException();

            return new Repository(this.name, this.lastModifiedDate);
        }
    }
}
