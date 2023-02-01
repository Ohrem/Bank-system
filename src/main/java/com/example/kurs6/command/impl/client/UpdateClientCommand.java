package com.example.kurs6.command.impl.client;

import com.example.kurs6.command.Command;
import com.example.kurs6.command.PagePath;
import com.example.kurs6.command.ParameterAndAttribute;
import com.example.kurs6.command.Router;
import com.example.kurs6.exception.CommandException;
import com.example.kurs6.exception.ServiceException;
import com.example.kurs6.model.service.impl.ClientServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static com.example.kurs6.command.PagePath.TO_MAIN_PAGE;
import static com.example.kurs6.command.ParameterAndAttribute.*;

public class UpdateClientCommand implements Command {
    private static ClientServiceImpl clientService = new ClientServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router(TO_MAIN_PAGE);
        Map<String, String> updateMap = getAllParams(request);

        try {
            if(clientService.updateClientInfo(updateMap)){
                request.setAttribute(LIST, clientService.findAll());
                router.setPagePath(PagePath.TO_ALL_USERS_PAGE);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return router;
    }

    private  Map<String, String> getAllParams(HttpServletRequest request) {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put(USER_ID, request.getParameter(USER_ID));
        paramsMap.put(USER_NAME, request.getParameter(ParameterAndAttribute.USER_NAME));
        paramsMap.put(USER_SURNAME, request.getParameter(ParameterAndAttribute.USER_SURNAME));
        paramsMap.put(USER_PATRONYMIC, request.getParameter(ParameterAndAttribute.USER_PATRONYMIC));
        paramsMap.put(USER_DOB, request.getParameter(ParameterAndAttribute.USER_DOB));
        paramsMap.put(SEX, request.getParameter(ParameterAndAttribute.SEX));
        paramsMap.put(PASSPORT_SERIAL, request.getParameter(ParameterAndAttribute.PASSPORT_SERIAL));
        paramsMap.put(PASSPORT_NUMBER, request.getParameter(ParameterAndAttribute.PASSPORT_NUMBER));
        paramsMap.put(PASSPORT_KEM_VIDAN, request.getParameter(ParameterAndAttribute.PASSPORT_KEM_VIDAN));
        paramsMap.put(PASSPORT_DOI, request.getParameter(ParameterAndAttribute.PASSPORT_DOI));
        paramsMap.put(USER_POB, request.getParameter(ParameterAndAttribute.USER_POB));
        paramsMap.put(USER_ID_NUMBER, request.getParameter(ParameterAndAttribute.USER_ID_NUMBER));
        paramsMap.put(USER_LIVING_CITY, request.getParameter(ParameterAndAttribute.USER_LIVING_CITY));
        paramsMap.put(USER_LIVING_ADDRESS, request.getParameter(ParameterAndAttribute.USER_LIVING_ADDRESS));
        paramsMap.put(USER_REGISTRATION_CITY, request.getParameter(ParameterAndAttribute.USER_REGISTRATION_CITY));
        paramsMap.put(USER_REGISTRATION_ADDRESS, request.getParameter(ParameterAndAttribute.USER_REGISTRATION_ADDRESS));
        paramsMap.put(USER_MARITAL_STATUS, request.getParameter(ParameterAndAttribute.USER_MARITAL_STATUS));
        paramsMap.put(USER_CITIZENSHIP, request.getParameter(ParameterAndAttribute.USER_CITIZENSHIP));
        paramsMap.put(USER_DISABILITY, request.getParameter(ParameterAndAttribute.USER_DISABILITY));
        paramsMap.put(USER_PENSIONER, request.getParameter(ParameterAndAttribute.USER_PENSIONER));
        paramsMap.put(USER_MILITARY_SERVICE, request.getParameter(ParameterAndAttribute.USER_MILITARY_SERVICE));
        return paramsMap;
    }
}
