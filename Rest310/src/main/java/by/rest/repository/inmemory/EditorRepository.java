// src/main/java/by/rest/repository/inmemory/EditorRepository.java
package by.rest.repository.inmemory;

import by.rest.domain.Editor;

public class EditorRepository extends InMemoryBaseRepository<Editor> {
    public EditorRepository() {
        super(Editor::getId, Editor::setId);
    }
}

