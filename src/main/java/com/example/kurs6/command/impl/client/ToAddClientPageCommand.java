package com.example.kurs6.command.impl.client;

import com.example.kurs6.command.Command;
import com.example.kurs6.command.PagePath;
import com.example.kurs6.command.Router;
import com.example.kurs6.exception.CommandException;

import jakarta.servlet.http.HttpServletRequest;


public class ToAddClientPageCommand implements Command {


    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        Router router = new Router();
        router.setPagePath(PagePath.TO_EDIT_USER_PAGE);


        return router;
    }
}

