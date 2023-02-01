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

import java.util.List;
import java.util.Optional;

import static com.example.kurs6.command.ParameterAndAttribute.*;

public class ToClientDepositsCommand implements Command {

    private static final DepositAgreementServiceImpl DEPOSIT_AGREEMENT_SERVICE = new DepositAgreementServiceImpl();
    private static final ClientServiceImpl CLIENT_SERVICE = new ClientServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, "all_user_deps");
        Router router = new Router();
        String userId = request.getParameter(USER_ID);

        try {
            List<DepositAgreement> depositAgreementList = DEPOSIT_AGREEMENT_SERVICE.findByUserId(Long.parseLong(userId));
            if(!depositAgreementList.isEmpty()) {
                request.setAttribute(LIST, depositAgreementList);
                request.setAttribute(USER_ID, userId);
                router.setPagePath(PagePath.TO_ALL_USER_DEPOSITS_PAGE);

            } else {
                router.setPagePath(PagePath.TO_ALL_USERS_PAGE);
                request.setAttribute(LIST, CLIENT_SERVICE.findAll());
                request.setAttribute("message","У пользователя нет активных депозитов");
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return router;
    }
}
