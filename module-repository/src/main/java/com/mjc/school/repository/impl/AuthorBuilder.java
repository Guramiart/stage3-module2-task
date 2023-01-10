package com.mjc.school.repository.impl;

import com.mjc.school.repository.builder.Builder;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;

@Scope("prototype")
public class AuthorBuilder implements Builder<AuthorModel> {


    private final AuthorModel authorModel = new AuthorModel();

    public AuthorBuilder setId(Long id) {
        authorModel.setId(id);
        return this;
    }

    public AuthorBuilder setName(String name) {
        authorModel.setName(name);
        return this;
    }

    public AuthorBuilder setCreateDate(LocalDateTime createDate) {
        authorModel.setCreateDate(createDate);
        return this;
    }

    public AuthorBuilder setLastUpdateDate(LocalDateTime lastUpdateDate) {
        authorModel.setLastUpdateDate(lastUpdateDate);
        return this;
    }

    @Override
    public AuthorModel build() {
        return authorModel;
    }
}
