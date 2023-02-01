package com.example.kurs6.enity;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Client extends AbstractEntity{
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate DOB;
    private Sex sex;

    private String passportSerial;
    private long passportNumber;
    private String kemVidan;
    private LocalDate dateOfIssue;
    private String idNumber;

    private String POB; //место рождения
    private City livingCity;
    private String livingAddress;
    private City registrationCity;
    private String registrationAddress;

    private MaritalStatus maritalStatus;
    private Country citizenship;
    private Disability disability;
    private Boolean pensioner;
    private Boolean militaryService;



    public enum Sex{
        MALE, FEMALE
    }

    public enum Country{
        BELARUS, RUSSIA
    }

    public enum City{
        MINSK, MOSCOW, BREST, VITEBSK, SPB, GRODNO
    }

    public enum Disability{
        NO, FIRST_GROUP, SECOND_GROUP, THIRD_GROUP
    }

    public enum MaritalStatus{
        MARRIED, NOT_MARRIED, DIVORCED
    }

    @Builder
    public Client(long id, String name, String surname, String patronymic, LocalDate DOB, Sex sex, String passportSerial, long passportNumber, String kemVidan, LocalDate dateOfIssue, String idNumber, String POB, City livingCity, String livingAddress, City registrationCity,
                  String registrationAddress, MaritalStatus maritalStatus, Country citizenship, Disability disability, Boolean pensioner, Boolean militaryService) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.DOB = DOB;
        this.sex = sex;
        this.passportSerial = passportSerial;
        this.passportNumber = passportNumber;
        this.kemVidan = kemVidan;
        this.dateOfIssue = dateOfIssue;
        this.idNumber = idNumber;
        this.POB = POB;
        this.livingCity = livingCity;
        this.livingAddress = livingAddress;
        this.registrationCity = registrationCity;
        this.registrationAddress = registrationAddress;
        this.maritalStatus = maritalStatus;
        this.citizenship = citizenship;
        this.disability = disability;
        this.pensioner = pensioner;
        this.militaryService = militaryService;
    }
}


