// src/main/java/by/rest/repository/inmemory/StoryRepository.java
package by.rest.repository.inmemory;

import by.rest.domain.Story;

public class StoryRepository extends InMemoryBaseRepository<Story> {
    public StoryRepository() {
        super(Story::getId, Story::setId);
    }
}

