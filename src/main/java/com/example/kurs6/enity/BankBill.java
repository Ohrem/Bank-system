package com.example.kurs6.enity;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BankBill extends AbstractEntity{

    BigDecimal balance;
    Long billNumber;


    @Builder
    public BankBill(long id, BigDecimal balance, Long billNumber) {
        super(id);
        this.balance = balance;
        this.billNumber = billNumber;
    }
}