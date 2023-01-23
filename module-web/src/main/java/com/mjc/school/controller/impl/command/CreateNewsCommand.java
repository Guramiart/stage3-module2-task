package com.mjc.school.controller.impl.command;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.Command;
import com.mjc.school.controller.constants.Constants;
import com.mjc.school.controller.utils.Utils;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exceptions.ServiceException;

import java.util.Scanner;


public class CreateNewsCommand implements Command {

    private static final String OPERATION = "Operation: Create news.";
    private final BaseController<NewsDtoRequest, NewsDtoResponse, Long> controller;
    private final Scanner sc;
    public CreateNewsCommand(BaseController<NewsDtoRequest, NewsDtoResponse, Long> controller, Scanner sc) {
        this.controller = controller;
        this.sc = sc;
    }

    @Override
    public void execute() {
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.println(OPERATION);
                System.out.println(Constants.NEWS_TITLE_RESP);
                String title = sc.nextLine();
                System.out.println(Constants.NEWS_CONTENT_RESP);
                String content = sc.nextLine();
                System.out.println(Constants.AUTHOR_ID_RESP);
                Long authorId = Utils.getNumberFromScanner("News", sc);
                NewsDtoRequest newsDtoRequest = new NewsDtoRequest(null, title, content, authorId);
                controller.create(newsDtoRequest);
                isValid = true;
            } catch (ServiceException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
