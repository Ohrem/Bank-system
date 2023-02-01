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

import java.util.Optional;

public class ToEditCreditPageCommand implements Command {
    private static final CreditAgreementServiceImpl service = new CreditAgreementServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String depositAgreementId = request.getParameter(ParameterAndAttribute.DEPOSIT_ID);
        try {
            Optional<DepositAgreement> optionalDepositAgreement = service.findById(Long.parseLong(depositAgreementId));
            if (optionalDepositAgreement.isPresent()){
                request.setAttribute(ParameterAndAttribute.DEPOSIT, optionalDepositAgreement.get());
                router.setPagePath(PagePath.TO_ADD_CREDIT_PAGE);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return router;
    }
}
