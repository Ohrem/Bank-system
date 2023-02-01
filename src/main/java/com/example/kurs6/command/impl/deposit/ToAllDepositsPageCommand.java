package com.example.kurs6.command.impl.deposit;

import com.example.kurs6.command.Command;
import com.example.kurs6.command.PagePath;
import com.example.kurs6.command.ParameterAndAttribute;
import com.example.kurs6.command.Router;
import com.example.kurs6.enity.DepositAgreement;
import com.example.kurs6.exception.CommandException;
import com.example.kurs6.exception.ServiceException;
import com.example.kurs6.model.service.impl.DepositAgreementServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class ToAllDepositsPageCommand implements Command {
    private static final DepositAgreementServiceImpl DEPOSIT_AGREEMENT_SERVICE = new DepositAgreementServiceImpl();


    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, "all_deps");
        Router router = new Router();
        try {
            List<DepositAgreement> agreementList = DEPOSIT_AGREEMENT_SERVICE.findAll();
            request.setAttribute(ParameterAndAttribute.LIST, agreementList);
            router.setPagePath(PagePath.TO_ALL_DEPOSITS_PAGE);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return router;
    }
}
