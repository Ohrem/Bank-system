package com.example.kurs6.command.impl.util;

import com.example.kurs6.command.ParameterAndAttribute;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

public class FillDeposit {
    public static Map<String, String> fill(HttpServletRequest request) {
        Map<String, String> fillMap = new HashMap<>();
        fillMap.put(ParameterAndAttribute.AGREEMENT_NUMBER, request.getParameter(ParameterAndAttribute.AGREEMENT_NUMBER));
        fillMap.put(ParameterAndAttribute.START_DATE, request.getParameter(ParameterAndAttribute.START_DATE));
        fillMap.put(ParameterAndAttribute.FINISH_DATE, request.getParameter(ParameterAndAttribute.FINISH_DATE));
        fillMap.put(ParameterAndAttribute.TERM_CONTRACT, request.getParameter(ParameterAndAttribute.TERM_CONTRACT));
        fillMap.put(ParameterAndAttribute.DEPOSIT_AMOUNT, request.getParameter(ParameterAndAttribute.DEPOSIT_AMOUNT));
        fillMap.put(ParameterAndAttribute.DEPOSIT_TYPE, request.getParameter(ParameterAndAttribute.DEPOSIT_TYPE));
        fillMap.put(ParameterAndAttribute.CURRENCY_TYPE, request.getParameter(ParameterAndAttribute.CURRENCY_TYPE));
        return fillMap;
    }

    public static Map<String, String> fillCredit(HttpServletRequest request) {
        Map<String, String> fillMap = new HashMap<>();
        fillMap.put(ParameterAndAttribute.AGREEMENT_NUMBER, request.getParameter(ParameterAndAttribute.AGREEMENT_NUMBER));
        fillMap.put(ParameterAndAttribute.START_DATE, request.getParameter(ParameterAndAttribute.START_DATE));
        fillMap.put(ParameterAndAttribute.FINISH_DATE, request.getParameter(ParameterAndAttribute.FINISH_DATE));
        fillMap.put(ParameterAndAttribute.TERM_CONTRACT, request.getParameter(ParameterAndAttribute.TERM_CONTRACT));
        fillMap.put(ParameterAndAttribute.DEPOSIT_AMOUNT, request.getParameter(ParameterAndAttribute.DEPOSIT_AMOUNT));
        fillMap.put(ParameterAndAttribute.CREDIT_TYPE, request.getParameter(ParameterAndAttribute.CREDIT_TYPE));
        fillMap.put(ParameterAndAttribute.CURRENCY_TYPE, request.getParameter(ParameterAndAttribute.CURRENCY_TYPE));
        return fillMap;
    }
}
