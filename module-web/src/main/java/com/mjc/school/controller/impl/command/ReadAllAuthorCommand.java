package com.mjc.school.controller.impl.command;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.Command;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;


public class ReadAllAuthorCommand implements Command {

    private static final String OPERATION = "Operation: Get all author.";
    private final BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> controller;

    public ReadAllAuthorCommand(BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        System.out.println(OPERATION);
        controller.readAll().forEach(System.out::println);
    }
}
