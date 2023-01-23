package com.mjc.school.controller.factory;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.Command;
import com.mjc.school.controller.impl.AuthorController;
import com.mjc.school.controller.impl.NewsController;
import com.mjc.school.controller.impl.command.*;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Component
public class CommandFactory {

    private final BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController;
    private final BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController;

    @Autowired
    public CommandFactory(BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController,
                          BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController) {
        this.newsController = newsController;
        this.authorController = authorController;
    }

    private enum Operation {
        CREATE_NEWS(1, "Create news") {
            @Override
            <T> Command getCommand(Scanner sc, T controller) {
                return new CreateNewsCommand((NewsController) controller, sc);
            }
        },
        CREATE_AUTHOR(2, "Create author") {
            @Override
            <T> Command getCommand(Scanner sc, T controller) {
                return new CreateAuthorCommand((AuthorController)controller, sc);
            }
        },
        GET_ALL_NEWS(3, "Get all news") {
            @Override
            <T> Command getCommand(Scanner sc, T controller) {
                return new ReadAllNewsCommand((NewsController) controller);
            }
        },
        GET_ALL_AUTHORS(4, "Get all authors") {
            @Override
            <T> Command getCommand(Scanner sc, T controller) {
                return new ReadAllAuthorCommand((AuthorController) controller);
            }
        },
        GET_NEWS_BY_ID(5, "Get news by id") {
            @Override
            <T> Command getCommand(Scanner sc, T controller) {
                return new ReadNewsByIdCommand((NewsController) controller, sc);
            }
        },
        GET_AUTHOR_BY_ID(6, "Get author by id") {
            @Override
            <T> Command getCommand(Scanner sc, T controller) {
                return new ReadAuthorByIdCommand((AuthorController) controller, sc);
            }
        },
        UPDATE_NEWS(7, "Update news") {
            @Override
            <T> Command getCommand(Scanner sc, T controller) {
                return new UpdateNewsCommand((NewsController) controller, sc);
            }
        },
        UPDATE_AUTHOR(8, "Update author") {
            @Override
            <T> Command getCommand(Scanner sc, T controller) {
                return new UpdateAuthorCommand((AuthorController)controller, sc);
            }
        },
        DELETE_NEWS(9, "Delete news") {
            @Override
            <T> Command getCommand(Scanner sc, T controller) {
                return new DeleteNewsCommand((NewsController) controller, sc);
            }
        },
        DELETE_AUTHOR(10, "Delete author") {
            @Override
            <T> Command getCommand(Scanner sc, T controller) {
                return new DeleteAuthorCommand((AuthorController) controller, sc);
            }
        },
        EXIT(0, "Exit") {
            @Override
            <T> Command getCommand(Scanner sc, T controller) {
                return new ExitCommand();
            }
        };

        private final int id;
        private final String name;

        Operation(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return id + " - " + name;
        }

        abstract <T> Command getCommand(Scanner sc, T controller);

    }

    public Command getCommand(Scanner sc) {
        Command command = new ErrorCommand();
        int id = Integer.parseInt(sc.nextLine());
        if(id >= 0 && id <= 10) {
            Operation operation = Arrays.stream(Operation.values())
                    .filter(e -> id == e.id)
                    .findFirst()
                    .get();
            if(id % 2 != 0) {
                command = operation.getCommand(sc, newsController);
            } else {
                command = operation.getCommand(sc, authorController);
            }
        }
        return command;
    }

    public List<String> getOperationList() {
        return Arrays.stream(Operation.values()).map(Operation::toString).toList();
    }

}
