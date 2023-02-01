package com.example.kurs6.controller;

import com.example.kurs6.command.Command;
import com.example.kurs6.command.CommandProvider;
import com.example.kurs6.command.PagePath;
import com.example.kurs6.command.Router;
import com.example.kurs6.model.connection.ConnectionPool;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

@WebServlet(urlPatterns = {"/controller"})
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Controller.class);

    @Override
    public void init() throws ServletException {
        ConnectionPool.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            Command command = CommandProvider.defineCommand(request);
            Router router = command.execute(request);

            switch (router.getType()) {
                case FORWARD -> {
                    logger.debug("forward");
                    RequestDispatcher dispatcher = request.getRequestDispatcher(router.getPagePath());
                    dispatcher.forward(request, response);
                }
                case REDIRECT -> {
                    logger.debug("redirect");
                    response.sendRedirect(router.getPagePath());
                }
                default -> {
                    logger.error("Incorrect router type:" + router.getType());
                    response.sendRedirect(PagePath.TO_MAIN_PAGE);
                }
            }
        } catch (Exception e) {
            response.sendError(SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
