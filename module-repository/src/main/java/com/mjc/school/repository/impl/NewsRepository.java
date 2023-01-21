package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.source.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository("newsRepo")
public class NewsRepository implements BaseRepository<NewsModel, Long> {

    private final DataSource dataSource;

    @Autowired
    public NewsRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<NewsModel> readAll() {
        return dataSource.getNewsModelList();
    }

    @Override
    public Optional<NewsModel> readById(Long id) {
        return dataSource.getNewsModelList()
                .stream()
                .filter(news -> Objects.equals(news.getId(), id))
                .findFirst();
    }

    @Override
    public NewsModel create(NewsModel entity) {
        List<NewsModel> modelList = dataSource.getNewsModelList();
        if(!modelList.isEmpty()) {
            entity.setId(modelList.get(modelList.size() - 1).getId() + 1L);
        } else {
            entity.setId(1L);
        }
        modelList.add(entity);
        return entity;
    }

    @Override
    public NewsModel update(NewsModel entity) {
        NewsModel newsModel = readById(entity.getId()).get();
        newsModel.setTitle(entity.getTitle());
        newsModel.setContent(entity.getContent());
        newsModel.setLastUpdateDate(LocalDateTime.now());
        newsModel.setAuthorId(entity.getAuthorId());
        return newsModel;
    }

    @Override
    public boolean deleteById(Long id) {
        return readById(id)
                .map(model -> dataSource.getNewsModelList().remove(model))
                .orElse(false);
    }

    @Override
    public boolean existById(Long id) {
        return dataSource.getNewsModelList()
                .stream()
                .anyMatch(news -> Objects.equals(news.getId(), id));
    }
}
