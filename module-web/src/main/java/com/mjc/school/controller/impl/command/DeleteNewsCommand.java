package com.mjc.school.controller.impl.command;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.Command;
import com.mjc.school.controller.constants.Constants;
import com.mjc.school.controller.utils.Utils;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exceptions.ServiceException;

import java.util.Scanner;

public class DeleteNewsCommand implements Command {

    private static final String OPERATION = "Operation: Delete news.";
    private final BaseController<NewsDtoRequest, NewsDtoResponse, Long> controller;
    private final Scanner sc;

    public DeleteNewsCommand(BaseController<NewsDtoRequest, NewsDtoResponse, Long> controller, Scanner sc) {
        this.controller = controller;
        this.sc = sc;
    }

    @Override
    public void execute() {
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.println(OPERATION);
                System.out.println(Constants.NEWS_ID_RESP);
                Long id = Utils.getNumberFromScanner("News", sc);
                System.out.println(controller.deleteById(id));
                isValid = true;
            }
            catch (ServiceException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
