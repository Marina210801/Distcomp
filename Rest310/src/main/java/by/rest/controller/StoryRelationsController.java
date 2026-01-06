// src/main/java/by/rest/controller/StoryRelationsController.java
package by.rest.controller;

import by.rest.exception.ApiException;
import by.rest.dto.EditorResponseTo;
import by.rest.dto.CommentResponseTo;
import by.rest.dto.TagResponseTo;
import by.rest.dto.StoryResponseTo;
import by.rest.service.CommentService;
import by.rest.service.EditorService;
import by.rest.service.StoryService;
import by.rest.service.TagService;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;
import java.util.List;

@RestController
@RequestMapping("/api/v1.0/stories")
public class StoryRelationsController {
    private final StoryService storyService;
    private final EditorService editorService;
    private final TagService tagService;
    private final CommentService commentService;

    public StoryRelationsController(StoryService storyService, EditorService editorService,
                                    TagService tagService, CommentService commentService) {
        this.storyService = storyService;
        this.editorService = editorService;
        this.tagService = tagService;
        this.commentService = commentService;
    }

    @GetMapping("/{id}/editor")
    public EditorResponseTo getEditorByStory(@PathVariable Long id) {
        StoryResponseTo story = storyService.getById(id);
        if (story.getEditorId() == null) {
            throw new ApiException(400, "40002", "Story has no editorId");
        }
        return editorService.getById(story.getEditorId());
    }


    @GetMapping("/{id}/comments")
    public List<CommentResponseTo> getCommentsByStory(@PathVariable Long id) {
        // InMemory: фильтрация всех комментариев по storyId
        return commentService.getAll().stream()
                .filter(c -> c.getStoryId().equals(id)).toList();
    }

    @GetMapping("/{id}/tags")
    public List<TagResponseTo> getTagsByStory(@PathVariable Long id) {
        StoryResponseTo story = storyService.getById(id);
        if (story.getTagIds() == null) return List.of();
        return story.getTagIds().stream().map(tagService::getById).toList();
    }

    // Поиск Stories по разным параметрам (простая фильтрация в памяти)
    @GetMapping("/search")
    public List<StoryResponseTo> search(
            @RequestParam(required = false) String tagName,
            @RequestParam(required = false) Long tagId,
            @RequestParam(required = false) String editorLogin,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String content
    ) {
        var all = storyService.getAll();
        return all.stream().filter(s -> {
            boolean ok = true;
            if (tagId != null) ok &= s.getTagIds() != null && s.getTagIds().contains(tagId);
            if (title != null && !title.isBlank()) ok &= s.getTitle() != null && s.getTitle().contains(title);
            if (content != null && !content.isBlank()) ok &= s.getContent() != null && s.getContent().contains(content);
            if (editorLogin != null && !editorLogin.isBlank()) {
                var editor = editorService.getById(s.getEditorId());
                ok &= editor.getLogin() != null && editor.getLogin().equals(editorLogin);
            }
            if (tagName != null && !tagName.isBlank()) {
                ok &= s.getTagIds() != null && s.getTagIds().stream()
                    .map(tagService::getById)
                    .filter(Objects::nonNull)
                    .anyMatch(t -> t.getName() != null && t.getName().equals(tagName));
            }

            return ok;
        }).toList();
    }
}
