package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.annotation.OnCascadeDelete;
import com.mjc.school.repository.source.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class AuthorRepository implements BaseRepository<AuthorModel, Long> {

    private final DataSource dataSource;

    @Autowired
    public AuthorRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<AuthorModel> readAll() {
        return dataSource.getAuthorModelList();
    }

    @Override
    public Optional<AuthorModel> readById(Long id) {
        return dataSource.getAuthorModelList()
                .stream()
                .filter(news -> Objects.equals(news.getId(), id))
                .findFirst();
    }

    @Override
    public AuthorModel create(AuthorModel entity) {
        List<AuthorModel> modelList = dataSource.getAuthorModelList();
        if(!modelList.isEmpty()) {
            entity.setId(modelList.get(modelList.size() - 1).getId() + 1L);
        } else {
            entity.setId(1L);
        }
        modelList.add(entity);
        return entity;
    }

    @Override
    public AuthorModel update(AuthorModel entity) {
        AuthorModel authorModel = readById(entity.getId()).get();
        authorModel.setName(entity.getName());
        authorModel.setLastUpdateDate(LocalDateTime.now());
        return authorModel;
    }

    @Override
    @OnCascadeDelete
    public boolean deleteById(Long id) {
        return readById(id)
                .map(model -> dataSource.getAuthorModelList().remove(model))
                .orElse(false);
    }

    @Override
    public boolean existById(Long id) {
        return dataSource.getAuthorModelList()
                .stream()
                .anyMatch(news -> Objects.equals(news.getId(), id));
    }
}
