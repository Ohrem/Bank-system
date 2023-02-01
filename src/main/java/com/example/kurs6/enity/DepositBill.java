package com.example.kurs6.enity;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class DepositBill extends AbstractEntity{
    Long number;
    BigDecimal billAmount;
    Long depositAgreementId;
    Long depositAgreementNumber;

    @Builder
    public DepositBill(long id, long number, BigDecimal billAmount, Long depositAgreementId, Long depositAgreementNumber) {
        super(id);
        this.number = number;
        this.billAmount = billAmount;
        this.depositAgreementId = depositAgreementId;
        this.depositAgreementNumber = depositAgreementNumber;
    }
}
