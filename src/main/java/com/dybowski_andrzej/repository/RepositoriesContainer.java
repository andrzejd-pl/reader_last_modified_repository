package com.dybowski_andrzej.repository;

import java.util.ArrayList;

public class RepositoriesContainer extends ArrayList<Repository> {

    public Repository getLastModifiedRepository() {
        Repository lastModified = get(0);
        for(Repository repository : this){
            if(lastModified.getLastModifiedDate().isBefore(repository.getLastModifiedDate()))
                lastModified = repository;
        }

        return lastModified;
    }
}
