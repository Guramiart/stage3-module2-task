package com.mjc.school.service.dto;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class NewsDtoRequest {

    private Long id;
    private String title;
    private String content;
    private Long authorId;

    public NewsDtoRequest(){}

    public NewsDtoRequest(Long id, String title, String content, Long authorId){
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsDtoRequest that = (NewsDtoRequest) o;
        return Objects.equals(id, that.id)
                && Objects.equals(title, that.title)
                && Objects.equals(content, that.content)
                && Objects.equals(authorId, that.authorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, authorId);
    }

    @Override
    public String toString() {
        return String.format("%s[id=%d, title=%s, content=%s, authorId=%d]",
                getClass().getSimpleName(), id, title, content, authorId);
    }
}
