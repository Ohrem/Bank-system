package com.example.kurs6.command.impl.credit;

import com.example.kurs6.command.Command;
import com.example.kurs6.command.PagePath;
import com.example.kurs6.command.ParameterAndAttribute;
import com.example.kurs6.command.Router;
import com.example.kurs6.enity.BankBill;
import com.example.kurs6.exception.CommandException;
import com.example.kurs6.exception.ServiceException;
import com.example.kurs6.model.service.impl.BankBillServiceImpl;
import com.example.kurs6.model.service.impl.CreditAgreementServiceImpl;
import com.example.kurs6.model.service.impl.DepositAgreementServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.example.kurs6.command.ParameterAndAttribute.*;
import static com.example.kurs6.command.ParameterAndAttribute.USER_ID;
import static com.example.kurs6.model.service.impl.BankBillServiceImpl.BANK_BILL_NUMBER;

public class CreateCreditCommand implements Command {
    CreditAgreementServiceImpl creditAgreementService = new CreditAgreementServiceImpl();
    BankBillServiceImpl bankBillService = new BankBillServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Map<String, String> depositData = new HashMap<>();
        Map<String, String> bankBillData = new HashMap<>();
        HttpSession session = request.getSession();

        Router router = new Router(PagePath.TO_MAIN_PAGE); //Переходим на страницу депозитов если будет

        String userId = request.getParameter(USER_ID);
        String name = request.getParameter(USER_NAME);
        String surname = request.getParameter(USER_SURNAME);
        String idNumber = request.getParameter(USER_ID_NUMBER);
        String agreement_number = request.getParameter(AGREEMENT_NUMBER);
        String start_date = request.getParameter(START_DATE);
        String finish_date = request.getParameter(FINISH_DATE);
        String term_contract = request.getParameter(TERM_CONTRACT);
        String deposit_amount = request.getParameter(DEPOSIT_AMOUNT);
        String credit_type = request.getParameter(CREDIT_TYPE);
        String currency_type = request.getParameter(CURRENCY_TYPE);

        depositData.put(USER_ID, userId);
        depositData.put(USER_NAME, name);
        depositData.put(USER_SURNAME, surname);
        depositData.put(USER_ID_NUMBER, idNumber);

        depositData.put(AGREEMENT_NUMBER, agreement_number);
        depositData.put(START_DATE, start_date);
        depositData.put(FINISH_DATE, finish_date);
        depositData.put(TERM_CONTRACT, term_contract);
        depositData.put(DEPOSIT_AMOUNT, deposit_amount);
        depositData.put(CREDIT_TYPE, credit_type);
        depositData.put(CURRENCY_TYPE, currency_type);

        try {
            Optional<BankBill> optionalBankBill = bankBillService.findById("1");
            BigDecimal userDepositAmount = new BigDecimal(deposit_amount);
            BigDecimal currentBankBalance = optionalBankBill.get().getBalance();

            bankBillData.put(ParameterAndAttribute.BALANCE, String.valueOf(currentBankBalance.subtract(userDepositAmount)));
            bankBillData.put(ParameterAndAttribute.BILL_NUMBER, String.valueOf(BANK_BILL_NUMBER));

            if (creditAgreementService.create(depositData)) {
                bankBillService.update("1", bankBillData);
//                router.setPagePath(PagePath.TO_MAIN_PAGE);
//                request.setAttribute(LIST, creditAgreementService.findByUserId(Long.parseLong(userId)));
                request.setAttribute(USER_ID, userId);
//                session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, "all_user_deps");
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return router;
    }
}
