// src/main/java/by/rest/service/EditorService.java
package by.rest.service;

import by.rest.domain.Editor;
import by.rest.dto.EditorRequestTo;
import by.rest.dto.EditorResponseTo;
import by.rest.exception.ApiException;
import by.rest.mapper.EditorMapper;
import by.rest.repository.inmemory.EditorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditorService {
    private final EditorRepository repo = new EditorRepository();
    private final EditorMapper mapper;

    public EditorService(EditorMapper mapper) {
        this.mapper = mapper;
    }

    public EditorResponseTo create(EditorRequestTo request) {
        validate(request);
        Editor e = mapper.toEntity(request);
        e = repo.save(e);
        return mapper.toResponse(e);
    }

    public List<EditorResponseTo> getAll() {
        return repo.findAll().stream().map(mapper::toResponse).toList();
    }

    public EditorResponseTo getById(Long id) {
        Editor e = repo.findById(id).orElseThrow(() ->
            new ApiException(404, "40401", "Editor not found"));
        return mapper.toResponse(e);
    }

    public EditorResponseTo update(Long id, EditorRequestTo request) {
        validate(request);
        Editor existing = repo.findById(id).orElseThrow(() ->
            new ApiException(404, "40401", "Editor not found"));
        Editor toUpdate = mapper.toEntity(request);
        toUpdate.setId(existing.getId());
        repo.update(id, toUpdate);
        return mapper.toResponse(toUpdate);
    }

    public void delete(Long id) {
        if (repo.findById(id).isEmpty())
            throw new ApiException(404, "40401", "Editor not found");
        repo.deleteById(id);
    }

    private void validate(EditorRequestTo r) {
        if (isBlank(r.getLogin()) || isBlank(r.getPassword())
            || isBlank(r.getFirstname()) || isBlank(r.getLastname())) {
            throw new ApiException(400, "40002", "Invalid editor fields");
        }
    }

    private boolean isBlank(String s) { return s == null || s.trim().isEmpty(); }
}
