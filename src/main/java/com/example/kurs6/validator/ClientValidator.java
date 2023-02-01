package com.example.kurs6.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ClientValidator {

    public static final Logger logger = LogManager.getLogger();

    private final static String NAME_REGEX = "^([\\p{Alpha}А-Яа-я]{1,15})$";
    private final static String SURNAME_REGEX = "^[\\p{Alpha}А-Яа-яЁё]{2,}-[\\p{Alpha}А-Яа-яЁё]{2,}$|^[\\p{Alpha}А-Яа-яЁё]{2,}$";
    private final static String PATRONYMIC_REGEX = "^([\\p{Alpha}А-Яа-я]{1,15})$";
    private final static String DOB_REGEX = "^(19|20)\\d\\d\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$"; //YYYY-MM-DD
    private final static String PASSPORTSERIAL_REGEX = "^\\d{7}[A-Za-z]\\d{3}[A-Za-z]{2}\\d$";
    private final static String PASSPORTNUMBER_REGEX = "^[A-Za-z]{2}\\d{7}$";
    private final static String KEMVIDAN_REGEX = "^[А-Яа-я]{0,20}\\s[А-Я]{0,20}\\s\\г\\.[А-Яа-я]{0,10}$"; //ex. Ленинское РУВД г.Минска
    private final static String DATEOFISSUE_REGEX = "^(19|20)\\d\\d\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$"; //YYYY-MM-DD
    private final static String IDNUMBER_REGEX = "3014\\d{8}\\d{0,1}";
    private final static String REGISTR_ADDRESS = "^([\\p{Alpha}А-Яа-я]{1,15})\\s\\d{1,2}\\.\\d$";
    private final static String LIVING_ADDRESS = "^([\\p{Alpha}А-Яа-я]{1,15})\\s\\d{1,2}\\.\\d$";

    private static boolean validateNull(String input) {
        return input != null && !input.isBlank();
    }

    public static boolean isValidName(String name) {
        return name.matches(NAME_REGEX) && validateNull(name);
    }

    public static boolean isValidSurname(String name) {
        return name.matches(SURNAME_REGEX) && validateNull(name);
    }

    public static boolean isValidPatronymic(String name) {
        return name.matches(PATRONYMIC_REGEX) && validateNull(name);
    }

    public static boolean isValidDOB(String name) {
        return name.matches(DOB_REGEX) && validateNull(name);
    }

    public static boolean isValidPassportSerial(String name) {
        return name.matches(PASSPORTSERIAL_REGEX) && validateNull(name);
    }

    public static boolean isValidPassportNumber(String name) {
        return name.matches(PASSPORTNUMBER_REGEX) && validateNull(name);
    }

    public static boolean isValidKemVidan(String name) {
        return name.matches(KEMVIDAN_REGEX) && validateNull(name);
    }

    public static boolean isValidDateOfIssue(String name) {
        return name.matches(DATEOFISSUE_REGEX) && validateNull(name);
    }

    public static boolean isValidIdNumber(String name) {
        return name.matches(IDNUMBER_REGEX) && validateNull(name);
    }

    public static boolean isValidRegistrationAddress(String name) {
        return name.matches(REGISTR_ADDRESS) && validateNull(name);
    }

    public static boolean isValidLivingAddress(String name) {
        return name.matches(LIVING_ADDRESS) && validateNull(name);
    }

    public static boolean validateClientFacade(String name, String surname, String patronymic,
                                               String DOB, String passportSerial, String passportNumber,
                                               String kemVidan, String dateOfIssue, String idNumber,
                                               String registrationAddress, String livingAddress) {
        return isValidName(name) && isValidSurname(surname) && isValidPatronymic(patronymic) &&
                isValidDOB(DOB) && isValidPassportSerial(passportSerial) && isValidPassportNumber(passportNumber) &&
                isValidKemVidan(kemVidan) && isValidDateOfIssue(dateOfIssue) && isValidIdNumber(idNumber) &&
                isValidRegistrationAddress(registrationAddress) && isValidLivingAddress(livingAddress);
    }
}