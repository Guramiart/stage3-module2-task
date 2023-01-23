package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.impl.AuthorModel;
import com.mjc.school.repository.impl.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.annotations.NotEmptyParam;
import com.mjc.school.service.annotations.ValidParam;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exceptions.ErrorCode;
import com.mjc.school.service.exceptions.ServiceException;
import com.mjc.school.service.interfaces.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("newsService")
public class NewsService implements BaseService<NewsDtoRequest, NewsDtoResponse, Long> {

    private final BaseRepository<NewsModel, Long> newsRepository;
    private final BaseRepository<AuthorModel, Long> authorRepository;

    @Autowired
    public NewsService(BaseRepository<NewsModel, Long> newsRepository,
                       BaseRepository<AuthorModel, Long> authorRepository) {
        this.newsRepository = newsRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<NewsDtoResponse> readAll() {
        return newsRepository.readAll().stream()
                .map(NewsMapper.INSTANCE::newsToNewsDto)
                .toList();
    }

    @Override
    @NotEmptyParam
    public NewsDtoResponse readById(Long id) {
        Optional<NewsModel> newsModel = newsRepository.readById(id);
        if(newsModel.isEmpty()) {
            throw new ServiceException(String.format(
                    ErrorCode.NOT_EXIST.getErrorMessage(), "News", id));
        }
        return NewsMapper.INSTANCE.newsToNewsDto(newsModel.get());
    }

    @Override
    @ValidParam
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        if(!authorRepository.existById(createRequest.getAuthorId())) {
            throw new ServiceException(String.format(
                    ErrorCode.NOT_EXIST.getErrorMessage(), "Author", createRequest.getAuthorId()));
        }
        NewsModel newsModel = newsRepository.create(NewsMapper.INSTANCE.newsDtoToNews(createRequest));
        return NewsMapper.INSTANCE.newsToNewsDto(newsModel);
    }

    @Override
    @ValidParam
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        if(!newsRepository.existById(updateRequest.getId())) {
            throw new ServiceException(String.format(
                    ErrorCode.NOT_EXIST.getErrorMessage(), "News", updateRequest.getId()));
        }
        NewsModel newsModel = newsRepository.update(NewsMapper.INSTANCE.newsDtoToNews(updateRequest));
        return NewsMapper.INSTANCE.newsToNewsDto(newsModel);
    }

    @Override
    @NotEmptyParam
    public boolean deleteById(Long id) {
        if(!newsRepository.existById(id)) {
            throw new ServiceException(String.format(ErrorCode.NOT_EXIST.getErrorMessage(), "News", id));
        }
        return newsRepository.deleteById(id);
    }

}
