package com.example.kurs6.command.impl.deposit;

import com.example.kurs6.command.Command;
import com.example.kurs6.command.PagePath;
import com.example.kurs6.command.ParameterAndAttribute;
import com.example.kurs6.command.Router;
import com.example.kurs6.enity.Client;
import com.example.kurs6.exception.CommandException;
import com.example.kurs6.exception.ServiceException;
import com.example.kurs6.model.service.impl.ClientServiceImpl;
import com.example.kurs6.model.service.impl.DepositAgreementServiceImpl;
import com.example.kurs6.util.IdGenerator;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class ToCreateDepositPageCommand implements Command {


    private static final ClientServiceImpl clientService = new ClientServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String userId = request.getParameter(ParameterAndAttribute.USER_ID);
        try {
            Optional<Client> client = clientService.findById(userId);

            System.out.println(client);

            if (client.isPresent() ) {
                router.setPagePath(PagePath.TO_ADD_DEPOSIT_PAGE);
                request.setAttribute(ParameterAndAttribute.AGREEMENT_NUMBER, IdGenerator.generateDepositAgreementNumber().get(0));
                request.setAttribute(ParameterAndAttribute.USER, client.get());
            } else {
                router.setPagePath(PagePath.TO_MAIN_PAGE);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}

