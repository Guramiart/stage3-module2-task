package com.mjc.school.repository.impl;

import com.mjc.school.repository.model.BaseEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Scope("prototype")
@Component("news")
public class NewsModel implements BaseEntity<Long> {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private Long authorId;

    public NewsModel() {}

    @Override
    public Long getId() {
        return id;
    }

    @Override
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public static NewsBuilder getBuilder() {
        return new NewsBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsModel newsModel = (NewsModel) o;
        return Objects.equals(id, newsModel.id)
                && Objects.equals(title, newsModel.title)
                && Objects.equals(content, newsModel.content)
                && Objects.equals(createDate, newsModel.createDate)
                && Objects.equals(lastUpdateDate, newsModel.lastUpdateDate)
                && Objects.equals(authorId, newsModel.authorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, createDate, lastUpdateDate, authorId);
    }

    @Override
    public String toString() {
        return String.format("%s[id=%d, title=%s, content=%s, createDate=%s, lastUpdateDate=%s, authorId=%d]",
                getClass().getSimpleName(), id, title, content, createDate, lastUpdateDate, authorId);
    }
}