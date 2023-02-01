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

import java.util.Optional;

public class ToEditClientCommand implements Command {
    private static final ClientServiceImpl clientService = new ClientServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        Router router = new Router();
        Optional<Client> client;
        String userId = request.getParameter(ParameterAndAttribute.USER_ID);

        try{
            client = clientService.findById(userId);

            if(client.isPresent()){

                router.setPagePath(PagePath.TO_EDIT_USER_PAGE);
                request.setAttribute(ParameterAndAttribute.USER, client.get());

            } else{
                router.setPagePath(PagePath.TO_MAIN_PAGE);
            }
        } catch (ServiceException e){
            throw new CommandException(e);
        }
        return router;
    }
}
