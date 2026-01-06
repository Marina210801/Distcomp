// src/main/java/by/rest/controller/CommentController.java
package by.rest.controller;

import by.rest.dto.CommentRequestTo;
import by.rest.dto.CommentResponseTo;
import by.rest.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1.0/comments")
public class CommentController {
    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CommentResponseTo> create(@RequestBody CommentRequestTo request) {
        CommentResponseTo res = service.create(request);
        return ResponseEntity.created(URI.create("/api/v1.0/comments/" + res.getId())).body(res);
    }

    @GetMapping
    public List<CommentResponseTo> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CommentResponseTo getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public CommentResponseTo update(@PathVariable Long id, @RequestBody CommentRequestTo request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
