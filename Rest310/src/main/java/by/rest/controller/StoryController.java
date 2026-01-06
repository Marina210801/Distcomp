// src/main/java/by/rest/controller/StoryController.java
package by.rest.controller;

import by.rest.dto.StoryRequestTo;
import by.rest.dto.StoryResponseTo;
import by.rest.service.StoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1.0/stories")
public class StoryController {
    private final StoryService service;

    public StoryController(StoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<StoryResponseTo> create(@RequestBody StoryRequestTo request) {
        StoryResponseTo res = service.create(request);
        return ResponseEntity.created(URI.create("/api/v1.0/stories/" + res.getId())).body(res);
    }

    @GetMapping
    public List<StoryResponseTo> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public StoryResponseTo getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public StoryResponseTo update(@PathVariable Long id, @RequestBody StoryRequestTo request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
