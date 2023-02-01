package com.example.kurs6.util;

import com.example.kurs6.enity.DepositAgreement;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BalanceCounter {

    static final Integer DAY_PER_YEAR = 36500;
    static final DecimalFormat df = new DecimalFormat("###.##");

    public static String countBalance(BigDecimal amount, DepositAgreement.DepositType depositType, int period) {
        BigDecimal depositTypePercent = BigDecimal.valueOf(depositType.getDeposit());
        BigDecimal days = BigDecimal.valueOf(period);
        BigDecimal prom = amount.multiply(depositTypePercent);
        prom = prom.multiply(days);
        prom = prom.divide(BigDecimal.valueOf(365), 2, RoundingMode.HALF_UP);
        prom = prom.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        return df.format((amount.multiply(depositTypePercent).multiply(days)).divide(BigDecimal.valueOf(DAY_PER_YEAR),2, RoundingMode.HALF_UP));
    }

    public static String countCreditBalance(BigDecimal amount, DepositAgreement.CreditType creditType, int period) {
        BigDecimal depositTypePercent = BigDecimal.valueOf(creditType.getCredit());
        BigDecimal days = BigDecimal.valueOf(period);
        BigDecimal prom = amount.multiply(depositTypePercent);
        System.out.println("1 " + prom);
        prom = prom.multiply(days);
        System.out.println("2 " + prom);
        prom = prom.divide(BigDecimal.valueOf(365), 2, RoundingMode.HALF_UP);
        System.out.println("3 " + prom);
        prom = prom.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        System.out.println("4 " + prom);
        return df.format((amount.multiply(depositTypePercent).multiply(days)).divide(BigDecimal.valueOf(DAY_PER_YEAR),2, RoundingMode.HALF_UP));
    }

    public static List<String> count(List<DepositAgreement> agreementList) {
        List<String> userSaldo = new ArrayList<>();
        for (int i = 0; i < agreementList.size(); i++) {
            DepositAgreement userAgreem = agreementList.get(i);
            int days = DayCounter.countDays(userAgreem.getStartDate(), userAgreem.getFinishDate());
            userSaldo.add(countBalance(userAgreem.getDepositAmount(), userAgreem.getDepositType(), days));
        }
        return userSaldo;
    }

}
