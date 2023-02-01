package com.example.kurs6.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DayCounter {

    public static int countDays(LocalDate date, LocalDate date2){
        return Math.toIntExact(ChronoUnit.DAYS.between(date, date2));
    }
}
