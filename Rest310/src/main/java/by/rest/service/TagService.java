// src/main/java/by/rest/service/TagService.java
package by.rest.service;

import by.rest.domain.Tag;
import by.rest.dto.TagRequestTo;
import by.rest.dto.TagResponseTo;
import by.rest.exception.ApiException;
import by.rest.mapper.TagMapper;
import by.rest.repository.inmemory.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    private final TagRepository repo = new TagRepository();
    private final TagMapper mapper;

    public TagService(TagMapper mapper) {
        this.mapper = mapper;
    }

    public TagResponseTo create(TagRequestTo request) {
        if (isBlank(request.getName()))
            throw new ApiException(400, "40002", "Invalid tag name");
        Tag t = mapper.toEntity(request);
        t = repo.save(t);
        return mapper.toResponse(t);
    }

    public List<TagResponseTo> getAll() {
        return repo.findAll().stream().map(mapper::toResponse).toList();
    }

    public TagResponseTo getById(Long id) {
        Tag t = repo.findById(id).orElseThrow(() ->
            new ApiException(404, "40401", "Tag not found"));
        return mapper.toResponse(t);
    }

    public TagResponseTo update(Long id, TagRequestTo request) {
        if (isBlank(request.getName()))
            throw new ApiException(400, "40002", "Invalid tag name");
        Tag existing = repo.findById(id).orElseThrow(() ->
            new ApiException(404, "40401", "Tag not found"));
        Tag toUpdate = mapper.toEntity(request);
        toUpdate.setId(existing.getId());
        repo.update(id, toUpdate);
        return mapper.toResponse(toUpdate);
    }

    public void delete(Long id) {
        if (repo.findById(id).isEmpty())
            throw new ApiException(404, "40401", "Tag not found");
        repo.deleteById(id);
    }

    private boolean isBlank(String s) { return s == null || s.trim().isEmpty(); }
}
