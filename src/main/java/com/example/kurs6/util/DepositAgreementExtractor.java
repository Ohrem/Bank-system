package com.example.kurs6.util;

import com.example.kurs6.command.ParameterAndAttribute;
import com.example.kurs6.enity.Client;
import com.example.kurs6.enity.DepositAgreement;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class DepositAgreementExtractor {

    public static DepositAgreement depositExtractor(Map<String, String> depositData){

        String userId = depositData.get(ParameterAndAttribute.USER_ID);
        String agreementNumber = depositData.get(ParameterAndAttribute.AGREEMENT_NUMBER);
        String startDate = depositData.get(ParameterAndAttribute.START_DATE);
        String finishDate = depositData.get(ParameterAndAttribute.FINISH_DATE);
        String termContract = depositData.get(ParameterAndAttribute.TERM_CONTRACT);
        String depositAmount = depositData.get(ParameterAndAttribute.DEPOSIT_AMOUNT);
        String depositType = depositData.get(ParameterAndAttribute.DEPOSIT_TYPE);
        String currencyType = depositData.get(ParameterAndAttribute.CURRENCY_TYPE);
        String surname = depositData.get(ParameterAndAttribute.USER_SURNAME);
        String name = depositData.get(ParameterAndAttribute.USER_NAME);
        String idNumber = depositData.get(ParameterAndAttribute.USER_ID_NUMBER);

        return DepositAgreement.builder()
                .agreementNumber(Long.parseLong(agreementNumber))
                .startDate(LocalDate.parse(startDate))
                .finishDate(LocalDate.parse(finishDate))
                .termContract(LocalDate.parse(termContract))
                .depositAmount(new BigDecimal(depositAmount))
                .depositType(DepositAgreement.DepositType.valueOf(depositType))
                .currencyType(DepositAgreement.CurrencyType.valueOf(currencyType))
                .client(Client.builder().id(Long.valueOf(userId)).surname(surname).name(name).idNumber(idNumber).build())
                .build();
    }

    public static DepositAgreement creditExtractor(Map<String, String> creditData){

        String userId = creditData.get(ParameterAndAttribute.USER_ID);
        String agreementNumber = creditData.get(ParameterAndAttribute.AGREEMENT_NUMBER);
        String startDate = creditData.get(ParameterAndAttribute.START_DATE);
        String finishDate = creditData.get(ParameterAndAttribute.FINISH_DATE);
        String termContract = creditData.get(ParameterAndAttribute.TERM_CONTRACT);
        String depositAmount = creditData.get(ParameterAndAttribute.DEPOSIT_AMOUNT);
        String creditType = creditData.get(ParameterAndAttribute.CREDIT_TYPE);
        String currencyType = creditData.get(ParameterAndAttribute.CURRENCY_TYPE);
        String surname = creditData.get(ParameterAndAttribute.USER_SURNAME);
        String name = creditData.get(ParameterAndAttribute.USER_NAME);
        String idNumber = creditData.get(ParameterAndAttribute.USER_ID_NUMBER);

        return DepositAgreement.builder()
                .agreementNumber(Long.parseLong(agreementNumber))
                .startDate(LocalDate.parse(startDate))
                .finishDate(LocalDate.parse(finishDate))
                .termContract(LocalDate.parse(termContract))
                .depositAmount(new BigDecimal(depositAmount))
                .creditType(DepositAgreement.CreditType.valueOf(creditType))
                .currencyType(DepositAgreement.CurrencyType.valueOf(currencyType))
                .client(Client.builder().id(Long.valueOf(userId)).surname(surname).name(name).idNumber(idNumber).build())
                .build();
    }
}
