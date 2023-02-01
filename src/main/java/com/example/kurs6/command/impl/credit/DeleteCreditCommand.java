package com.example.kurs6.command.impl.credit;

import com.example.kurs6.command.Command;
import com.example.kurs6.command.PagePath;
import com.example.kurs6.command.ParameterAndAttribute;
import com.example.kurs6.command.Router;
import com.example.kurs6.exception.CommandException;
import com.example.kurs6.exception.ServiceException;
import com.example.kurs6.model.service.impl.ClientServiceImpl;
import com.example.kurs6.model.service.impl.CreditAgreementServiceImpl;
import com.example.kurs6.model.service.impl.DepositAgreementServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.example.kurs6.command.ParameterAndAttribute.LIST;
import static com.example.kurs6.command.ParameterAndAttribute.USER_ID;

public class DeleteCreditCommand implements Command {
    CreditAgreementServiceImpl creditAgreementService = new CreditAgreementServiceImpl();
    ClientServiceImpl clientService = new ClientServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
        Router router = new Router(PagePath.TO_MAIN_PAGE); //Переходим на результат добавления депозита
        String userId = request.getParameter(USER_ID);
        try {
            if (creditAgreementService.delete(Long.parseLong(request.getParameter(ParameterAndAttribute.DEPOSIT_ID)))) {
                if (currentPage.equalsIgnoreCase("all_creds")) {
                    request.setAttribute(LIST, creditAgreementService.findAll());
                    router.setPagePath(PagePath.TO_ALL_DEPOSITS_PAGE);
                } else if (currentPage.equalsIgnoreCase("all_user_creds")) {
                    router.setPagePath(PagePath.TO_ALL_USER_CREDITS_PAGE);
                    request.setAttribute(LIST, creditAgreementService.findByUserId(Long.parseLong(userId)));
                    request.setAttribute(USER_ID, userId);
                }
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return router;
    }
}
