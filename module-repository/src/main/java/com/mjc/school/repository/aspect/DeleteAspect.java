package com.mjc.school.repository.aspect;

import com.mjc.school.repository.impl.AuthorModel;
import com.mjc.school.repository.impl.NewsModel;
import com.mjc.school.repository.impl.NewsRepository;
import com.mjc.school.repository.source.DataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Aspect
@Component
public class DeleteAspect {

    private final DataSource dataSource;

    @Autowired
    private DeleteAspect(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Pointcut("@annotation(com.mjc.school.repository.annotation.OnCascadeDelete)")
    public void deleteMethods() {}

    @Before("deleteMethods()")
    public void cascadeDelete(JoinPoint joinPoint) {
        Long id = (Long)joinPoint.getArgs()[0];
        List<NewsModel> newsModelList = dataSource.getNewsModelList();
        List<NewsModel> deletedList = newsModelList
                .stream()
                .filter(news -> Objects.equals(news.getAuthorId(), id))
                .toList();
        newsModelList.removeAll(deletedList);
    }
}
