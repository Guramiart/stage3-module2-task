package com.mjc.school.repository.source;

import com.mjc.school.repository.constants.Constants;
import com.mjc.school.repository.impl.AuthorBuilder;
import com.mjc.school.repository.impl.AuthorModel;
import com.mjc.school.repository.impl.NewsBuilder;
import com.mjc.school.repository.impl.NewsModel;
import com.mjc.school.repository.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataSource {

    private static DataSource instance;
    private final Random random = new Random();
    private final List<NewsModel> newsModelList;
    private final List<AuthorModel> authorModelList;

    @Autowired
    private DataSource() {
        this.authorModelList = initAuthorList();
        this.newsModelList = initNewsList();
    }

    private List<AuthorModel> initAuthorList() {
        List<AuthorModel> authors = new ArrayList<>();
        for (long i = 1; i <= Constants.GENERATE_DATA_LIMIT; i++) {
            LocalDateTime localDateTime = Utils.getRandomDate();
            authors.add(new AuthorBuilder()
                    .setId(i)
                    .setName(Utils.getRandomData(Constants.AUTHOR_FILE))
                    .setCreateDate(localDateTime)
                    .setLastUpdateDate(localDateTime)
                    .build());
        }
        return authors;
    }

    private List<NewsModel> initNewsList() {
        List<NewsModel> news = new ArrayList<>();
        for (long i = 1; i <= Constants.GENERATE_DATA_LIMIT ; i++) {
            LocalDateTime localDateTime = Utils.getRandomDate();
            news.add(new NewsBuilder()
                    .setId(i)
                    .setTitle(Utils.getRandomData(Constants.NEWS_FILE))
                    .setContent(Utils.getRandomData(Constants.CONTENT_FILE))
                    .setCreateDate(localDateTime)
                    .setLastUpdateDate(localDateTime)
                    .setAuthorId(authorModelList.get(random.nextInt(authorModelList.size())).getId())
                    .build());
        }
        return news;
    }

    public List<AuthorModel> getAuthorModelList() {
        return authorModelList;
    }

    public List<NewsModel> getNewsModelList() {
        return newsModelList;
    }
}
