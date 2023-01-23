package com.mjc.school.controller.impl.command;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.Command;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;


public class ReadAllNewsCommand implements Command {

    private static final String OPERATION = "Operation: Get all news.";
    private final BaseController<NewsDtoRequest, NewsDtoResponse, Long> controller;

    public ReadAllNewsCommand(BaseController<NewsDtoRequest, NewsDtoResponse, Long> controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        System.out.println(OPERATION);
        controller.readAll().forEach(System.out::println);
    }
}
