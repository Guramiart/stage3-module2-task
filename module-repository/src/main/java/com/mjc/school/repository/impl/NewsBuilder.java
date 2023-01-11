package com.mjc.school.repository.impl;

import com.mjc.school.repository.builder.Builder;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;

@Scope("prototype")
public class NewsBuilder implements Builder<NewsModel> {

    private final NewsModel newsModel = new NewsModel();

    public NewsBuilder setId(Long id) {
        newsModel.setId(id);
        return this;
    }

    public NewsBuilder setTitle(String title) {
        newsModel.setTitle(title);
        return this;
    }

    public NewsBuilder setContent(String content) {
        newsModel.setContent(content);
        return this;
    }

    public NewsBuilder setCreateDate(LocalDateTime createDate) {
        newsModel.setCreateDate(createDate);
        return this;
    }

    public NewsBuilder setLastUpdateDate(LocalDateTime lastUpdateDate) {
        newsModel.setLastUpdateDate(lastUpdateDate);
        return this;
    }

    public NewsBuilder setAuthorId(Long authorId) {
        newsModel.setAuthorId(authorId);
        return this;
    }

    @Override
    public NewsModel build() {
        return newsModel;
    }
}
