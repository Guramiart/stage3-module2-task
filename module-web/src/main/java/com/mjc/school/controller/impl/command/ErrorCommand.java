package com.mjc.school.controller.impl.command;

import com.mjc.school.controller.Command;

public class ErrorCommand implements Command {

    private static final String TITLE = "Command not found!";
    @Override
    public void execute() {
        System.out.println(TITLE);
    }
}
