package com.example.kurs6.model.dao.mapper;


import com.example.kurs6.enity.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static com.example.kurs6.command.ParameterAndAttribute.*;

public class ClientRowMapper {
    private static final String ID = "user_id";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String THIRD_NAME = "third_name";
    private static final String DOB = "date_of_birth";
    private static final String SEX = "sex";

    public Client mapRow(ResultSet rs) throws SQLException {

        Client client = Client.builder().id(rs.getLong(ID)).
                name(rs.getString(NAME)).surname(rs.getString(SURNAME))
                .patronymic(rs.getString(THIRD_NAME))
                .DOB(LocalDate.parse(rs.getString(DOB)))
                .sex(Client.Sex.valueOf(rs.getString(SEX)))
                .passportSerial(rs.getString("passport_serial"))
                .passportNumber(rs.getLong("passport_number"))
                .kemVidan(rs.getString("output_organisation"))
                .dateOfIssue(LocalDate.parse(rs.getString("output_date")))
                .idNumber(rs.getString("identification_number"))
                .POB(rs.getString("place_of_birth"))
                .livingCity(Client.City.valueOf(rs.getString("living_city")))
                .livingAddress(rs.getString(("living_address")))
                .registrationCity(Client.City.valueOf(rs.getString("registration_city")))//
                .registrationAddress(rs.getString("registration_address"))
                .maritalStatus(Client.MaritalStatus.valueOf(rs.getString("marital_status")))
                .citizenship(Client.Country.valueOf(rs.getString("citizenship")))
                .disability(Client.Disability.valueOf(rs.getString("is_invalid")))
                .pensioner(Boolean.valueOf(rs.getString(("is_pensiya"))))
                .militaryService(Boolean.valueOf(rs.getString(("is_conscript")))).build();
        return client;
    }

    public Client mapRowLite(ResultSet rs) throws SQLException {
        Client client = Client.builder()
                .id(rs.getLong(ID))
                .name(rs.getString(NAME))
                .surname(rs.getString(SURNAME))
                .patronymic(rs.getString(THIRD_NAME))
                .DOB(LocalDate.parse(rs.getString(DOB)))
                .sex(Client.Sex.valueOf(rs.getString(SEX).toUpperCase()))
                .build();
        return client;
    }
}

