// src/main/java/by/rest/service/StoryService.java
package by.rest.service;

import by.rest.domain.Story;
import by.rest.dto.StoryRequestTo;
import by.rest.dto.StoryResponseTo;
import by.rest.exception.ApiException;
import by.rest.mapper.StoryMapper;
import by.rest.repository.inmemory.StoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StoryService {
    private final StoryRepository repo = new StoryRepository();
    private final StoryMapper mapper;
    private final EditorService editorService; // для проверки editorId
    // Tag проверки можно добавить позднее

    public StoryService(StoryMapper mapper, EditorService editorService) {
        this.mapper = mapper;
        this.editorService = editorService;
    }

    public StoryResponseTo create(StoryRequestTo request) {
        validate(request);
        // проверка существования редактора
        editorService.getById(request.getEditorId());
        Story s = mapper.toEntity(request);
        s = repo.save(s);
        return mapper.toResponse(s);
    }

    public List<StoryResponseTo> getAll() {
        return repo.findAll().stream().map(mapper::toResponse).toList();
    }

    public StoryResponseTo getById(Long id) {
        Story s = repo.findById(id).orElseThrow(() ->
            new ApiException(404, "40401", "Story not found"));
        return mapper.toResponse(s);
    }

    public StoryResponseTo update(Long id, StoryRequestTo request) {
        validate(request);
        editorService.getById(request.getEditorId());
        Story existing = repo.findById(id).orElseThrow(() ->
            new ApiException(404, "40401", "Story not found"));
        Story toUpdate = mapper.toEntity(request);
        toUpdate.setId(existing.getId());
        repo.update(id, toUpdate);
        return mapper.toResponse(toUpdate);
    }

    public void delete(Long id) {
        if (repo.findById(id).isEmpty())
            throw new ApiException(404, "40401", "Story not found");
        repo.deleteById(id);
    }

    private void validate(StoryRequestTo r) {
        if (r.getEditorId() == null || isBlank(r.getTitle()) || isBlank(r.getContent())) {
            throw new ApiException(400, "40002", "Invalid story fields");
        }
        if (r.getTagIds() != null && r.getTagIds().stream().anyMatch(Objects::isNull)) {
            throw new ApiException(400, "40002", "Invalid tagIds");
        }
    }

    private boolean isBlank(String s) { return s == null || s.trim().isEmpty(); }
}
