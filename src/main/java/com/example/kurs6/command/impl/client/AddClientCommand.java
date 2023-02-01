package com.example.kurs6.command.impl.client;

import com.example.kurs6.command.Command;
import com.example.kurs6.command.PagePath;
import com.example.kurs6.command.ParameterAndAttribute;
import com.example.kurs6.command.Router;

import com.example.kurs6.exception.CommandException;
import com.example.kurs6.exception.ServiceException;
import com.example.kurs6.model.service.impl.ClientServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static com.example.kurs6.command.PagePath.TO_MAIN_PAGE;
import static com.example.kurs6.command.ParameterAndAttribute.LIST;

public class AddClientCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Logger logger = LogManager.getLogger();
        Router router = new Router(TO_MAIN_PAGE);
        ClientServiceImpl clientService = new ClientServiceImpl();

        Map<String, String> clientData = new HashMap<>();


        clientData.put(ParameterAndAttribute.USER_NAME, request.getParameter(ParameterAndAttribute.USER_NAME));
        clientData.put(ParameterAndAttribute.USER_SURNAME, request.getParameter(ParameterAndAttribute.USER_SURNAME));
        clientData.put(ParameterAndAttribute.USER_PATRONYMIC, request.getParameter(ParameterAndAttribute.USER_PATRONYMIC));
        clientData.put(ParameterAndAttribute.USER_DOB, request.getParameter(ParameterAndAttribute.USER_DOB));
        clientData.put(ParameterAndAttribute.SEX, request.getParameter(ParameterAndAttribute.SEX));
        clientData.put(ParameterAndAttribute.PASSPORT_SERIAL, request.getParameter(ParameterAndAttribute.PASSPORT_SERIAL));
        clientData.put(ParameterAndAttribute.PASSPORT_NUMBER, request.getParameter(ParameterAndAttribute.PASSPORT_NUMBER));
        clientData.put(ParameterAndAttribute.PASSPORT_KEM_VIDAN, request.getParameter(ParameterAndAttribute.PASSPORT_KEM_VIDAN));
        clientData.put(ParameterAndAttribute.PASSPORT_DOI, request.getParameter(ParameterAndAttribute.PASSPORT_DOI));
        clientData.put(ParameterAndAttribute.USER_POB, request.getParameter(ParameterAndAttribute.USER_POB));
        clientData.put(ParameterAndAttribute.USER_ID_NUMBER, request.getParameter(ParameterAndAttribute.USER_ID_NUMBER));
        clientData.put(ParameterAndAttribute.USER_LIVING_CITY, request.getParameter(ParameterAndAttribute.USER_LIVING_CITY));
        clientData.put(ParameterAndAttribute.USER_LIVING_ADDRESS, request.getParameter(ParameterAndAttribute.USER_LIVING_ADDRESS));
        clientData.put(ParameterAndAttribute.USER_REGISTRATION_CITY, request.getParameter(ParameterAndAttribute.USER_REGISTRATION_CITY));
        clientData.put(ParameterAndAttribute.USER_REGISTRATION_ADDRESS, request.getParameter(ParameterAndAttribute.USER_REGISTRATION_ADDRESS));
        clientData.put(ParameterAndAttribute.USER_MARITAL_STATUS, request.getParameter(ParameterAndAttribute.USER_MARITAL_STATUS));
        clientData.put(ParameterAndAttribute.USER_CITIZENSHIP, request.getParameter(ParameterAndAttribute.USER_CITIZENSHIP));
        clientData.put(ParameterAndAttribute.USER_DISABILITY, request.getParameter(ParameterAndAttribute.USER_DISABILITY));
        clientData.put(ParameterAndAttribute.USER_PENSIONER, request.getParameter(ParameterAndAttribute.USER_PENSIONER));
        clientData.put(ParameterAndAttribute.USER_MILITARY_SERVICE, request.getParameter(ParameterAndAttribute.USER_MILITARY_SERVICE));

        try {
            if (clientService.create(clientData)) {
                request.setAttribute(LIST, clientService.findAll());
                router.setPagePath(PagePath.TO_ALL_USERS_PAGE);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return router;
    }
}
