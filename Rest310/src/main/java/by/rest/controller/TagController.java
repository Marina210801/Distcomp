// src/main/java/by/rest/controller/TagController.java
package by.rest.controller;

import by.rest.dto.TagRequestTo;
import by.rest.dto.TagResponseTo;
import by.rest.service.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1.0/tags")
public class TagController {
    private final TagService service;

    public TagController(TagService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TagResponseTo> create(@RequestBody TagRequestTo request) {
        TagResponseTo res = service.create(request);
        return ResponseEntity.created(URI.create("/api/v1.0/tags/" + res.getId())).body(res);
    }

    @GetMapping
    public List<TagResponseTo> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public TagResponseTo getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public TagResponseTo update(@PathVariable Long id, @RequestBody TagRequestTo request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
