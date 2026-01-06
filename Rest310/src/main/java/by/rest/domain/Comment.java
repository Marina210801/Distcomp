package by.rest.domain;

public class Comment {
    private Long id;
    private Long storyId; // связь Story -> Comment (one-to-many)
    private String content;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getStoryId() { return storyId; }
    public void setStoryId(Long storyId) { this.storyId = storyId; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
