package com.example.kurs6.command.impl.deposit;

import com.example.kurs6.command.Command;
import com.example.kurs6.command.PagePath;
import com.example.kurs6.command.ParameterAndAttribute;
import com.example.kurs6.command.Router;
import com.example.kurs6.enity.Client;
import com.example.kurs6.enity.DepositAgreement;
import com.example.kurs6.exception.CommandException;
import com.example.kurs6.exception.ServiceException;
import com.example.kurs6.model.service.impl.ClientServiceImpl;
import com.example.kurs6.model.service.impl.DepositAgreementServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

import static com.example.kurs6.command.ParameterAndAttribute.USER;
import static com.example.kurs6.command.ParameterAndAttribute.USER_ID;

public class ToEditDepositPageCommand implements Command {
    private static final DepositAgreementServiceImpl service = new DepositAgreementServiceImpl();
    private static final ClientServiceImpl userService = new ClientServiceImpl();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String depositAgreementId = request.getParameter(ParameterAndAttribute.DEPOSIT_ID);
        try {
            Optional<DepositAgreement> optionalDepositAgreement = service.findById(Long.parseLong(depositAgreementId));
            if (optionalDepositAgreement.isPresent()){
                request.setAttribute(ParameterAndAttribute.DEPOSIT, optionalDepositAgreement.get());
                router.setPagePath(PagePath.TO_ADD_DEPOSIT_PAGE);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return router;
    }
}
