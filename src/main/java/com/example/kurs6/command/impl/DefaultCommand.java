package com.example.kurs6.command.impl;

import com.example.kurs6.command.Command;
import com.example.kurs6.command.PagePath;
import com.example.kurs6.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DefaultCommand implements Command {
    private static final Logger logger = LogManager.getLogger(DefaultCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setPagePath(PagePath.TO_MAIN_PAGE);
        logger.debug("Returning to main page");
        return router;
    }
}
