package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.impl.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.annotations.NotEmptyParam;
import com.mjc.school.service.annotations.ValidParam;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.interfaces.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("newsService")
public class NewsService implements BaseService<NewsDtoRequest, NewsDtoResponse, Long> {

    private final BaseRepository<NewsModel, Long> newsRepository;

    @Autowired
    public NewsService(BaseRepository<NewsModel, Long> newsRepository) {
        this.newsRepository = newsRepository;
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
        NewsDtoResponse newsDtoResponse = new NewsDtoResponse();
        if(newsModel.isPresent() && newsRepository.existById(id)) {
            newsDtoResponse = NewsMapper.INSTANCE.newsToNewsDto(newsModel.get());
        }
        return newsDtoResponse;
    }

    @Override
    @ValidParam
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        NewsModel newsModel = newsRepository.create(NewsMapper.INSTANCE.newsDtoToNews(createRequest));
        return NewsMapper.INSTANCE.newsToNewsDto(newsModel);
    }

    @Override
    @ValidParam
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        NewsModel newsModel = newsRepository.update(NewsMapper.INSTANCE.newsDtoToNews(updateRequest));
        return NewsMapper.INSTANCE.newsToNewsDto(newsModel);
    }

    @Override
    @NotEmptyParam
    public boolean deleteById(Long id) {
        return newsRepository.deleteById(id);
    }

}
