package com.example.kurs6.command.impl.credit;

import com.example.kurs6.command.Command;
import com.example.kurs6.command.PagePath;
import com.example.kurs6.command.ParameterAndAttribute;
import com.example.kurs6.command.Router;
import com.example.kurs6.enity.DepositAgreement;
import com.example.kurs6.exception.CommandException;
import com.example.kurs6.exception.ServiceException;
import com.example.kurs6.model.service.impl.ClientServiceImpl;
import com.example.kurs6.model.service.impl.CreditAgreementServiceImpl;
import com.example.kurs6.model.service.impl.DepositAgreementServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.core.util.JsonUtils;

import java.util.List;

import static com.example.kurs6.command.ParameterAndAttribute.LIST;
import static com.example.kurs6.command.ParameterAndAttribute.USER_ID;

public class ToClientCreditsCommand implements Command {
    private static final CreditAgreementServiceImpl creditAgreementService = new CreditAgreementServiceImpl();
    private static final ClientServiceImpl CLIENT_SERVICE = new ClientServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, "all_user_creds");
        Router router = new Router();
        String userId = request.getParameter(USER_ID);
        System.out.println(userId);
        try {
            List<DepositAgreement> depositAgreementList = creditAgreementService.findByUserId(Long.parseLong(userId));
            if(!depositAgreementList.isEmpty()) {
                System.out.println("not empty");
                request.setAttribute(LIST, depositAgreementList);
                request.setAttribute(USER_ID, userId);
                router.setPagePath(PagePath.TO_ALL_USER_CREDITS_PAGE);
            } else {
                System.out.println("empty");
                router.setPagePath(PagePath.TO_ALL_USERS_PAGE);
                request.setAttribute(LIST, CLIENT_SERVICE.findAll());
                request.setAttribute("message","У пользователя нет активных кредитов");
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return router;
    }
}
