package com.example.kurs6.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public  class IdGenerator {
    private static final Long LOW = 10000000L;
    private static final Long HIGH = 99999999L;

    public static List<Long> generateDepositAgreementNumber() {
        List<Long> longList = new ArrayList<>();
        Random r = new Random();
        String result = String.valueOf(r.nextLong(HIGH - LOW) + LOW);

        for (int i = 0; i <2; i++) {
            int key = generateUniqKey();
            Long preResult = Long.valueOf(3014 + result + key);
            longList.add(preResult);
        }
        return longList;
    }

    private static int generateUniqKey() {
        Random rand = new Random();
        return rand.nextInt((10) + 1);
    }
}
