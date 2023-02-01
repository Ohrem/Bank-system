package com.example.kurs6.command.impl.client;

import com.example.kurs6.command.Command;
import com.example.kurs6.command.PagePath;
import com.example.kurs6.command.ParameterAndAttribute;
import com.example.kurs6.command.Router;
import com.example.kurs6.enity.Client;
import com.example.kurs6.exception.CommandException;
import com.example.kurs6.exception.ServiceException;
import com.example.kurs6.model.service.impl.ClientServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class FindAllClientsCommand implements Command {
    ClientServiceImpl clientService = new ClientServiceImpl();
    Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router(PagePath.TO_ALL_USERS_PAGE);

        try {
            List<Client> allUsers = clientService.findAll();
            request.setAttribute(ParameterAndAttribute.LIST, allUsers);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}
