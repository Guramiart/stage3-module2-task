package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.impl.AuthorModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.annotations.NotEmptyParam;
import com.mjc.school.service.annotations.ValidParam;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.interfaces.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("authorService")
public class AuthorService implements BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> {

    private final BaseRepository<AuthorModel, Long> authorRepository;

    @Autowired
    public AuthorService(BaseRepository<AuthorModel, Long> authorRepository){
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorDtoResponse> readAll() {
        return authorRepository.readAll()
                .stream()
                .map(AuthorMapper.INSTANCE::authorToAuthorDto)
                .toList();
    }

    @Override
    @NotEmptyParam
    public AuthorDtoResponse readById(Long id) {
        Optional<AuthorModel> authorModel = authorRepository.readById(id);
        AuthorDtoResponse authorDtoResponse = new AuthorDtoResponse();
        if(authorModel.isPresent() && authorRepository.existById(id)) {
            authorDtoResponse = AuthorMapper.INSTANCE.authorToAuthorDto(authorModel.get());
        }
        return authorDtoResponse;
    }

    @Override
    @ValidParam
    public AuthorDtoResponse create(AuthorDtoRequest createRequest) {
        AuthorModel authorModel = authorRepository
                .create(AuthorMapper.INSTANCE.authorDtoToAuthor(createRequest));
        return AuthorMapper.INSTANCE.authorToAuthorDto(authorModel);
    }

    @Override
    @ValidParam
    public AuthorDtoResponse update(AuthorDtoRequest updateRequest) {
        AuthorModel authorModel = authorRepository
                .update(AuthorMapper.INSTANCE.authorDtoToAuthor(updateRequest));
        return AuthorMapper.INSTANCE.authorToAuthorDto(authorModel);
    }

    @Override
    @NotEmptyParam
    public boolean deleteById(Long id) {
        return authorRepository.deleteById(id);
    }
}
