package com.example.kurs6.command.impl;

import com.example.kurs6.command.Command;
import com.example.kurs6.command.PagePath;
import com.example.kurs6.command.ParameterAndAttribute;
import com.example.kurs6.command.Router;
import com.example.kurs6.enity.DepositAgreement;
import com.example.kurs6.enity.DepositBill;
import com.example.kurs6.exception.CommandException;
import com.example.kurs6.exception.ServiceException;
import com.example.kurs6.model.service.impl.*;
import com.example.kurs6.util.BalanceCounter;
import com.example.kurs6.util.DayCounter;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static com.example.kurs6.command.ParameterAndAttribute.*;

public class ToBankDayClosing implements Command {
    private final DepositAgreementServiceImpl depositAgreementService = new DepositAgreementServiceImpl();
    private final MainBillServiceImpl mainBillService = new MainBillServiceImpl();
    private final ServiceBillServiceImpl serviceBillService = new ServiceBillServiceImpl();

    private final CreditAgreementServiceImpl creditAgreementService = new CreditAgreementServiceImpl();
    private final CreditMainBillServiceImpl creditMainBillService = new CreditMainBillServiceImpl();
    private final CreditServiceBillServiceImpl creditServiceBillService = new CreditServiceBillServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        Router router = new Router();
        List<DepositBill> mainBillsList = new ArrayList<>();
        List<DepositBill> serviceBillsList = new ArrayList<>();

        List<DepositBill> creditMainBillsList = new ArrayList<>();
        List<DepositBill> creditServiceBillsList = new ArrayList<>();

        try {
            List<DepositAgreement> userDepositAgreementList = depositAgreementService.findAll();
            for (DepositAgreement depositAgreement : userDepositAgreementList) {
                Long depositAgreementNumber = depositAgreement.getAgreementNumber();
                DepositBill mainBill = mainBillService.findByAgreementNumber(depositAgreementNumber).get();

                int days = DayCounter.countDays(depositAgreement.getStartDate(), depositAgreement.getFinishDate());
                String saldo = BalanceCounter.countBalance(mainBill.getBillAmount(), depositAgreement.getDepositType(), days).replaceAll(",", ".");
                System.out.println(saldo);
                Map<String, String> serviceBillData = new HashMap<>();
                serviceBillData.put(BILL_AMOUNT, saldo);
                serviceBillData.put(DEPOSIT_AGREEMENT_NUMBER, String.valueOf(depositAgreementNumber));
                serviceBillService.update(serviceBillData);

                DepositBill updatedServiceBill = serviceBillService.findByAgreementNumber(depositAgreementNumber).get();
                System.out.println(updatedServiceBill);
                mainBillsList.add(mainBill);
                serviceBillsList.add(updatedServiceBill);
            }
            List<Integer> evenIndexedNames = IntStream
                    .range(0, mainBillsList.size())
                    .boxed().toList();

            List<DepositAgreement> userCreditAgreementList = creditAgreementService.findAll();
            System.out.println(userCreditAgreementList);
            for (DepositAgreement creditAgreement : userCreditAgreementList) {
                Long depositAgreementNumber = creditAgreement.getAgreementNumber();
                DepositBill mainBill = creditMainBillService.findByAgreementNumber(depositAgreementNumber).get();

                int days = DayCounter.countDays(creditAgreement.getStartDate(), creditAgreement.getFinishDate());
                String saldo = BalanceCounter.countCreditBalance(mainBill.getBillAmount(), creditAgreement.getCreditType(), days).replaceAll(",", ".");
                System.out.println(saldo);
                Map<String, String> creditServiceBillData = new HashMap<>();
                creditServiceBillData.put(BILL_AMOUNT, saldo);
                creditServiceBillData.put(DEPOSIT_AGREEMENT_NUMBER, String.valueOf(depositAgreementNumber));
                creditServiceBillService.update(creditServiceBillData);

                DepositBill updatedServiceBill = creditServiceBillService.findByAgreementNumber(depositAgreementNumber).get();
                System.out.println(updatedServiceBill);
                creditMainBillsList.add(mainBill);
                creditServiceBillsList.add(updatedServiceBill);
            }

            List<Integer> evenCreditIndexedNames = IntStream
                    .range(0, creditMainBillsList.size())
                    .boxed().toList();
            request.setAttribute(AGREEMENT, userDepositAgreementList);
            request.setAttribute(MAIN_BILL, mainBillsList);
            request.setAttribute(SERVICE_BILL, serviceBillsList);
            request.setAttribute(INDEXES, evenIndexedNames);

            request.setAttribute(CREDIT_AGREEMENT, userCreditAgreementList);
            request.setAttribute(CREDIT_MAIN_BILL, creditMainBillsList);
            request.setAttribute(CREDIT_SERVICE_BILL, creditServiceBillsList);
            request.setAttribute(CREDIT_INDEXES, evenCreditIndexedNames);
            router.setPagePath(PagePath.TO_CLOSE_BANK_DAY);

        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return router;
    }
}
