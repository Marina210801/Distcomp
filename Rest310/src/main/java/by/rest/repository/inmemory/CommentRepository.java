// src/main/java/by/rest/repository/inmemory/CommentRepository.java
package by.rest.repository.inmemory;

import by.rest.domain.Comment;

public class CommentRepository extends InMemoryBaseRepository<Comment> {
    public CommentRepository() {
        super(Comment::getId, Comment::setId);
    }
}
