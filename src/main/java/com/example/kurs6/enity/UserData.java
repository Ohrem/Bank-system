package com.example.kurs6.enity;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserData extends AbstractEntity{

    private String schetNumber;  //нужна валидация по заданию
    private BillActivity billActivity;

    private enum BillActivity{
        ACTIV,PASSIV
    }

    @Builder
    public UserData(Long id,String schetNumber, BillActivity billActivity) {
        super(id);
        this.schetNumber = schetNumber;
        this.billActivity = billActivity;
    }
}
