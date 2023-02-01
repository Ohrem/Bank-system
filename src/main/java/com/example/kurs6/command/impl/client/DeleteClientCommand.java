package com.example.kurs6.command.impl.client;

import com.example.kurs6.command.Command;
import com.example.kurs6.command.PagePath;
import com.example.kurs6.command.ParameterAndAttribute;
import com.example.kurs6.command.Router;
import com.example.kurs6.exception.CommandException;
import com.example.kurs6.exception.ServiceException;
import com.example.kurs6.model.service.impl.ClientServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class DeleteClientCommand implements Command {
    ClientServiceImpl clientService = new ClientServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router(PagePath.TO_MAIN_PAGE);
        String deleteUserById = request.getParameter(ParameterAndAttribute.USER_ID);

        try {
            if(clientService.deleteById(deleteUserById)){
                request.setAttribute(ParameterAndAttribute.LIST, clientService.findAll());
                router.setPagePath(PagePath.TO_ALL_USERS_PAGE);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return router;
    }
}
