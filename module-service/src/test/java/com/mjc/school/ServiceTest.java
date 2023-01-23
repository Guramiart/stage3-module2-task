package com.mjc.school;

import com.mjc.school.repository.impl.AuthorBuilder;
import com.mjc.school.repository.impl.NewsBuilder;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exceptions.ServiceException;
import com.mjc.school.service.interfaces.AuthorMapper;
import com.mjc.school.service.interfaces.NewsMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig
class ServiceTest {

    @Configuration
    @ComponentScan({"com.mjc.school.repository", "com.mjc.school.service"})
    static class NewsTestConfig {}

    private final NewsDtoRequest newsDtoTestRequest =
            new NewsDtoRequest(null, "NewsTitle", "NewsContent", 10L);
    private final AuthorDtoRequest authorDtoTestRequest =
            new AuthorDtoRequest(null, "Author");
    @Autowired
    private BaseService<NewsDtoRequest, NewsDtoResponse, Long> newsService;
    @Autowired
    private BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> authorService;

    @Test
    void readAll() {
        System.out.println(newsService.readAll());
        assertNotNull(newsService.readAll());
        assertNotNull(authorService.readAll());
    }

    @Test
    void entityToEntityDtoTest() {
        NewsDtoResponse newsDtoResponse = NewsMapper.INSTANCE.newsToNewsDto(new NewsBuilder()
                .setId(1L)
                .setTitle("TestTitle")
                .setContent("TestContent")
                .setAuthorId(1L)
                .build());
        assertEquals("TestTitle", newsDtoResponse.getTitle());
        assertEquals("TestContent", newsDtoResponse.getContent());
        assertEquals(1L, newsDtoResponse.getAuthorId());

        AuthorDtoResponse authorDtoResponse = AuthorMapper.INSTANCE.authorToAuthorDto(new AuthorBuilder()
                .setId(1L)
                .setName("TestName")
                .build());
        assertEquals("TestName", authorDtoResponse.getName());
        assertEquals(1L, authorDtoResponse.getId());
    }

    @Test
    void readEntityByIdTest() {
        Long id = 10L;
        NewsDtoResponse newsDtoResponse = newsService.readById(id);
        AuthorDtoResponse authorDtoResponse = authorService.readById(id);
        assertNotNull(newsDtoResponse);
        assertEquals(id, newsDtoResponse.getId());
        assertNotNull(authorDtoResponse);
        assertEquals(id, authorDtoResponse.getId());
    }

    @Test
    void createEntityTest() {
        assertNotNull(newsService.create(new NewsDtoRequest(
                null, "NewsTitle", "NewsContent", 10L)));
        NewsDtoResponse newsDtoResponse = newsService.readById(21L);
        assertNotNull(newsDtoResponse.getTitle());
        assertNotNull(newsDtoResponse.getCreateDate());
        assertNotNull(newsDtoResponse.getLastUpdateDate());

        assertNotNull(authorService.create(new AuthorDtoRequest(null, "Author")));
        AuthorDtoResponse authorDtoResponse = authorService.readById(21L);
        assertNotNull(authorDtoResponse.getName());
        assertNotNull(authorDtoResponse.getCreateDate());
        assertNotNull(authorDtoResponse.getLastUpdateDate());
    }

    @Test
    void updateEntityTest() {
        NewsDtoResponse newsDtoResponse = newsService.readById(10L);
        assertNotEquals(newsDtoResponse.getTitle(), newsDtoTestRequest.getTitle());
        newsDtoTestRequest.setId(10L);
        newsService.update(newsDtoTestRequest);
        assertEquals(newsService.readById(10L).getTitle(), newsDtoTestRequest.getTitle());
        assertEquals(newsService.readById(10L).getContent(), newsDtoTestRequest.getContent());

        AuthorDtoResponse authorDtoResponse = authorService.readById(10L);
        assertNotEquals(authorDtoResponse.getName(), authorDtoTestRequest.getName());
        authorDtoTestRequest.setId(10L);
        authorService.update(authorDtoTestRequest);
        assertEquals(authorService.readById(10L).getName(), authorDtoTestRequest.getName());
    }

    @Test
    void deleteEntityTest() {
        assertNotNull(newsService.readById(7L));
        newsService.deleteById(7L);
        assertEquals(0, newsService.readAll()
                .stream()
                .filter(el -> Objects.equals(el.getId(), 7L))
                .toList()
                .size());
        assertNotNull(authorService.readById(5L));
        authorService.deleteById(5L);
        assertEquals(0, authorService.readAll()
                .stream()
                .filter(el -> Objects.equals(el.getId(), 5L))
                .toList()
                .size());
    }

    @Test
    void validationTest() {
        assertThrows(ServiceException.class, () -> newsService.readById(-1L));
        assertThrows(ServiceException.class, () -> newsService.deleteById(-1L));
        assertThrows(ServiceException.class, () -> authorService.readById(-1L));
        assertThrows(ServiceException.class, () -> authorService.deleteById(-1L));
    }
}
