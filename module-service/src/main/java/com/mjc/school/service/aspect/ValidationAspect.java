package com.mjc.school.service.aspect;

import static com.mjc.school.service.constants.Constants.*;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.validator.Validator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidationAspect {

    @Before(value = "@annotation(com.mjc.school.service.annotations.NotEmptyParam) " +
            "&& args(id)")
    private void validateNumber(JoinPoint joinPoint, Long id) {
        String param = joinPoint.getSourceLocation().getWithinType()
                .getSimpleName().replace("Service", "");
        Validator.validateNumber(id, param);
    }

    @Before(value = "@annotation(com.mjc.school.service.annotations.ValidParam) " +
            "&& args(newsRequest)")
    private void validateNewsParam(NewsDtoRequest newsRequest) {
        Validator.validateString(newsRequest.getTitle(),
                TITLE_PARAM, MIN_TITLE_LENGTH, MAX_TITLE_LENGTH);
        Validator.validateString(newsRequest.getContent(),
                CONTENT_PARAM, MIN_CONTENT_LENGTH, MAX_CONTENT_LENGTH);
        Validator.validateNumber(newsRequest.getAuthorId(), AUTHOR_PARAM);
    }

    @Before(value = "@annotation(com.mjc.school.service.annotations.ValidParam) " +
            "&& args(authorRequest)")
    public void validateAuthorParam(AuthorDtoRequest authorRequest) {
        Validator.validateString(authorRequest.getName(),
                AUTHOR_NAME_PARAM, MIN_AUTHOR_NAME_LENGTH, MAX_AUTHOR_NAME_LENGTH);
    }
}
