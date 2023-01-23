package com.mjc.school.service.interfaces;

import com.mjc.school.repository.impl.AuthorModel;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDtoResponse authorToAuthorDto(AuthorModel authorModel);

    AuthorModel authorDtoToAuthor(AuthorDtoRequest authorDtoRequest);

}
