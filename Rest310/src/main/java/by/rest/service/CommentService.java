// src/main/java/by/rest/service/CommentService.java
package by.rest.service;

import by.rest.domain.Comment;
import by.rest.dto.CommentRequestTo;
import by.rest.dto.CommentResponseTo;
import by.rest.exception.ApiException;
import by.rest.mapper.CommentMapper;
import by.rest.repository.inmemory.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository repo = new CommentRepository();
    private final CommentMapper mapper;
    private final StoryService storyService;

    public CommentService(CommentMapper mapper, StoryService storyService) {
        this.mapper = mapper;
        this.storyService = storyService;
    }

    public CommentResponseTo create(CommentRequestTo request) {
        validate(request);
        // проверка существования story
        storyService.getById(request.getStoryId());
        Comment c = mapper.toEntity(request);
        c = repo.save(c);
        return mapper.toResponse(c);
    }

    public List<CommentResponseTo> getAll() {
        return repo.findAll().stream().map(mapper::toResponse).toList();
    }

    public CommentResponseTo getById(Long id) {
        Comment c = repo.findById(id).orElseThrow(() ->
            new ApiException(404, "40401", "Comment not found"));
        return mapper.toResponse(c);
    }

    public CommentResponseTo update(Long id, CommentRequestTo request) {
        validate(request);
        storyService.getById(request.getStoryId());
        Comment existing = repo.findById(id).orElseThrow(() ->
            new ApiException(404, "40401", "Comment not found"));
        Comment toUpdate = mapper.toEntity(request);
        toUpdate.setId(existing.getId());
        repo.update(id, toUpdate);
        return mapper.toResponse(toUpdate);
    }

    public void delete(Long id) {
        if (repo.findById(id).isEmpty())
            throw new ApiException(404, "40401", "Comment not found");
        repo.deleteById(id);
    }

    private void validate(CommentRequestTo r) {
        if (r.getStoryId() == null || isBlank(r.getContent()))
            throw new ApiException(400, "40002", "Invalid comment fields");
    }

    private boolean isBlank(String s) { return s == null || s.trim().isEmpty(); }
}
