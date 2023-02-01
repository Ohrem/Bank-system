package com.example.kurs6.command.impl.deposit;

import com.example.kurs6.command.Command;
import com.example.kurs6.command.PagePath;
import com.example.kurs6.command.ParameterAndAttribute;
import com.example.kurs6.command.Router;
import com.example.kurs6.exception.CommandException;
import com.example.kurs6.exception.ServiceException;
import com.example.kurs6.model.service.impl.DepositAgreementServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.example.kurs6.command.PagePath.TO_MAIN_PAGE;
import static com.example.kurs6.command.ParameterAndAttribute.LIST;
import static com.example.kurs6.command.ParameterAndAttribute.USER_ID;
import static com.example.kurs6.command.impl.util.FillDeposit.fill;

public class UpdateDepositCommand implements Command {
    private final DepositAgreementServiceImpl depositAgreementService = new DepositAgreementServiceImpl();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
        Logger logger = LogManager.getLogger();
        Router router = new Router(TO_MAIN_PAGE);
        String depositId = request.getParameter(ParameterAndAttribute.DEPOSIT_ID);
        String userId = request.getParameter(USER_ID);
        System.out.println(userId);
        System.out.println(depositId);
        try {
            if (depositAgreementService.update(Long.parseLong(depositId), fill(request))){
                if (currentPage.equalsIgnoreCase("all_deps")){
                    request.setAttribute(LIST, depositAgreementService.findAll());
                    router.setPagePath(PagePath.TO_ALL_DEPOSITS_PAGE);
                } else if(currentPage.equalsIgnoreCase("all_user_deps")) {
                    router.setPagePath(PagePath.TO_ALL_USER_DEPOSITS_PAGE);
                    request.setAttribute(LIST, depositAgreementService.findByUserId(Long.parseLong(userId)));
                    request.setAttribute(USER_ID, userId);
                }
            } else {
                logger.info("ВСЕ ПЛОХО");
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return router;
    }
}
