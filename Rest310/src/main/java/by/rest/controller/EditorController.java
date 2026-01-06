// src/main/java/by/rest/controller/EditorController.java
package by.rest.controller;

import by.rest.dto.EditorRequestTo;
import by.rest.dto.EditorResponseTo;
import by.rest.service.EditorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1.0/editors")
public class EditorController {
    private final EditorService service;

    public EditorController(EditorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EditorResponseTo> create(@RequestBody EditorRequestTo request) {
        EditorResponseTo res = service.create(request);
        return ResponseEntity.created(URI.create("/api/v1.0/editors/" + res.getId())).body(res);
    }

    @GetMapping
    public List<EditorResponseTo> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public EditorResponseTo getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public EditorResponseTo update(@PathVariable Long id, @RequestBody EditorRequestTo request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
