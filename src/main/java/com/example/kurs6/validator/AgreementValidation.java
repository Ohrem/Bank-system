package com.example.kurs6.validator;

import java.util.regex.Pattern;

public class AgreementValidation {
    private final static String IDNUMBER_REGEX = "3014\\d{8}\\d{0,1}";
    private final static String DATE_REGEX = "^(19|20)\\d\\d\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$"; //YYYY-MM-DD
    private final static String DEPOSITAMOUNT_REGEX = "\\d{0,15}";

    private static boolean validateNull(String input) {
        return !input.isBlank() && input != null;
    }

    public static boolean isValidIdNumber(String input) {
        return input.matches(IDNUMBER_REGEX) && validateNull(input);
    }

    public static boolean isValidDate(String input) {
        return input.matches(DATE_REGEX) && validateNull(input);
    }

    public static boolean isValidDepositAmount(String input) {
        return input.matches(DEPOSITAMOUNT_REGEX) && validateNull(input);
    }

}
