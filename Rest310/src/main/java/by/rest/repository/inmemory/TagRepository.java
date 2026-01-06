// src/main/java/by/rest/repository/inmemory/TagRepository.java
package by.rest.repository.inmemory;

import by.rest.domain.Tag;

public class TagRepository extends InMemoryBaseRepository<Tag> {
    public TagRepository() {
        super(Tag::getId, Tag::setId);
    }
}

