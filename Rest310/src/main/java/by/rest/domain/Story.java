package by.rest.domain;

import java.util.HashSet;
import java.util.Set;

public class Story {
    private Long id;
    private Long editorId;    // связь Editor -> Story (one-to-many)
    private String title;
    private String content;
    private Set<Long> tagIds = new HashSet<>(); // many-to-many Story<->Tag

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getEditorId() { return editorId; }
    public void setEditorId(Long editorId) { this.editorId = editorId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Set<Long> getTagIds() { return tagIds; }
    public void setTagIds(Set<Long> tagIds) { this.tagIds = tagIds; }
}
