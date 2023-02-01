package com.example.kurs6.enity;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class DepositAgreement extends AbstractEntity {

    long agreementNumber;
    LocalDate startDate;
    LocalDate finishDate;
    LocalDate termContract;
    BigDecimal depositAmount;
    DepositType depositType;
    CreditType creditType;
    CurrencyType currencyType;
    Client client;


    @Getter
    public enum DepositType{
        PERPETUAL(7),LONG_TERM(10),SHORT_TERM(4);

        private final double deposit;

        DepositType(double deposit) {
            this.deposit = deposit;
        }
    }

    @Getter
    public enum CreditType{
        PERPETUAL(12),LONG_TERM(17),SHORT_TERM(21);

        private final double credit;

        CreditType(double credit) {
            this.credit = credit;
        }
    }

    public enum CurrencyType{
        BYN,USD,EUR
    }

    @Builder
    public DepositAgreement(long id, long agreementNumber, LocalDate startDate, LocalDate finishDate,
                            LocalDate termContract, BigDecimal depositAmount,
                            DepositType depositType, CreditType creditType, CurrencyType currencyType, Client client) {
        super(id);
        this.agreementNumber = agreementNumber;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.termContract = termContract;
        this.depositAmount = depositAmount;
        this.depositType = depositType;
        this.creditType = creditType;
        this.currencyType = currencyType;
        this.client = client;
    }
}
