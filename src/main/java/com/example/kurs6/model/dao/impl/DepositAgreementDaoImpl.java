package com.example.kurs6.model.dao.impl;

import com.example.kurs6.enity.Client;
import com.example.kurs6.enity.DepositAgreement;
import com.example.kurs6.exception.DaoException;
import com.example.kurs6.model.connection.ConnectionPool;
import com.example.kurs6.model.dao.DepositAgreementDao;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepositAgreementDaoImpl implements DepositAgreementDao {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String SQL_INSERT_DEPOSIT = "INSERT INTO deposit_agreement (agreement_number, start_date, finish_date, term_contract, deposit_amount, deposit_type, currency_type, users_user_id) VALUES (?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE_DEPOSIT = "UPDATE deposit_agreement SET start_date = ?, finish_date = ?, term_contract = ?, deposit_amount = ?, deposit_type = ?, currency_type = ? WHERE agreement_number = ?";
    private static final String SQL_DELETE_DEPOSIT = "DELETE FROM deposit_agreement WHERE deposit_agreement_id = ?";
    private static final String SQL_FIND_ALL_DEPOSIT = "select deposit_agreement.deposit_agreement_id, deposit_agreement.agreement_number, deposit_agreement.start_date, deposit_agreement.finish_date, deposit_agreement.term_contract, deposit_agreement.deposit_amount, deposit_agreement.deposit_type, deposit_agreement.currency_type, deposit_agreement.users_user_id, users.name, users.surname, users.third_name, passports.identification_number from deposit_agreement " +
            "INNER JOIN users on deposit_agreement.users_user_id = users.user_id " +
            "INNER JOIN passports on users.user_id = passports.users_user_id";
    private static final String SQL_FIND_ALL_DEPOSITS_BY_USER_ID = "select deposit_agreement.deposit_agreement_id, deposit_agreement.agreement_number, deposit_agreement.start_date, deposit_agreement.finish_date, deposit_agreement.term_contract, deposit_agreement.deposit_amount, deposit_agreement.deposit_type, deposit_agreement.currency_type, deposit_agreement.users_user_id, users.name, users.surname, passports.identification_number from deposit_agreement INNER JOIN users on deposit_agreement.users_user_id = users.user_id INNER JOIN passports on users.user_id = passports.users_user_id where deposit_agreement.users_user_id = ?";
    private static final String SQL_FIND_DEPOSIT_BY_ID = "select deposit_agreement.deposit_agreement_id, deposit_agreement.agreement_number, deposit_agreement.start_date, deposit_agreement.finish_date, deposit_agreement.term_contract, deposit_agreement.deposit_amount, deposit_agreement.deposit_type, deposit_agreement.currency_type, deposit_agreement.users_user_id, users.name, users.surname, users.third_name, passports.identification_number from deposit_agreement INNER JOIN users on deposit_agreement.users_user_id = users.user_id INNER JOIN passports on users.user_id = passports.users_user_id where deposit_agreement.deposit_agreement_id = ?";
    private static DepositAgreementDaoImpl INSTANCE;

    private DepositAgreementDaoImpl(){
    }

    public static DepositAgreementDaoImpl getInstance(){
        if (INSTANCE == null){
            INSTANCE = new DepositAgreementDaoImpl();
        }
        return INSTANCE;
    }

    @Override
    public boolean create(DepositAgreement depositAgreement) throws DaoException {
        boolean result = false;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_DEPOSIT)) {
            statement.setLong(1, depositAgreement.getAgreementNumber());
            statement.setString(2, depositAgreement.getStartDate().format(DateTimeFormatter.ISO_DATE));
            statement.setString(3, depositAgreement.getFinishDate().format(DateTimeFormatter.ISO_DATE));
            statement.setString(4, depositAgreement.getTermContract().format(DateTimeFormatter.ISO_DATE));
            statement.setBigDecimal(5, depositAgreement.getDepositAmount());
            statement.setString(6, depositAgreement.getDepositType().toString());
            statement.setString(7, depositAgreement.getCurrencyType().toString());
            statement.setLong(8, depositAgreement.getClient().getId());

            if (statement.executeUpdate() != 0) {
                result = true;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    public Long createAndReturnId(DepositAgreement depositAgreement) throws DaoException {
        Long id = 0L;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_DEPOSIT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, depositAgreement.getAgreementNumber());
            statement.setString(2, depositAgreement.getStartDate().format(DateTimeFormatter.ISO_DATE));
            statement.setString(3, depositAgreement.getFinishDate().format(DateTimeFormatter.ISO_DATE));
            statement.setString(4, depositAgreement.getTermContract().format(DateTimeFormatter.ISO_DATE));
            statement.setBigDecimal(5, depositAgreement.getDepositAmount());
            statement.setString(6, depositAgreement.getDepositType().toString());
            statement.setString(7, depositAgreement.getCurrencyType().toString());
            statement.setLong(8, depositAgreement.getClient().getId());
            if (statement.executeUpdate() != 0) {
                try (ResultSet rs = statement.getGeneratedKeys()){
                    if (rs.next()){
                        id = rs.getLong(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return id;
    }

    @Override
    public boolean update(DepositAgreement depositAgreement) throws DaoException{
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_DEPOSIT)) {
            statement.setString(1, depositAgreement.getStartDate().format(DateTimeFormatter.ISO_DATE));
            statement.setString(2, depositAgreement.getFinishDate().format(DateTimeFormatter.ISO_DATE));
            statement.setString(3, depositAgreement.getTermContract().format(DateTimeFormatter.ISO_DATE));
            statement.setBigDecimal(4, depositAgreement.getDepositAmount());
            statement.setString(5, depositAgreement.getDepositType().toString());
            statement.setString(6, depositAgreement.getCurrencyType().toString());
            statement.setLong(7, depositAgreement.getAgreementNumber());
            if (statement.executeUpdate() != 0) {
                result = true;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean delete(DepositAgreement depositAgreement) throws DaoException{
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_DEPOSIT)) {
            statement.setLong(1, depositAgreement.getId());
            if (statement.executeUpdate() != 0) {
                result = true;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<DepositAgreement> findAll() throws DaoException{
        List<DepositAgreement> depositAgreementList = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_DEPOSIT);
             ResultSet rs = statement.executeQuery()){
            while (rs.next()){
                DepositAgreement depositAgreement = DepositAgreement.builder()
                        .id(rs.getLong(1))
                        .agreementNumber(rs.getLong(2))
                        .startDate(LocalDate.parse(rs.getString(3)))
                        .finishDate(LocalDate.parse(rs.getString(4)))
                        .termContract(LocalDate.parse(rs.getString(5)))
                        .depositAmount(rs.getBigDecimal(6))
                        .depositType(DepositAgreement.DepositType.valueOf(rs.getString(7)))
                        .currencyType(DepositAgreement.CurrencyType.valueOf(rs.getString(8)))
                        .client(Client.builder()
                                .id(rs.getLong(9))
                                .name(rs.getString(10))
                                .surname(rs.getString(11))
                                .patronymic(rs.getString(12))
                                .idNumber(rs.getString(13))
                                .build())
                        .build();
                depositAgreementList.add(depositAgreement);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return depositAgreementList;
    }

    public List<DepositAgreement> findByUserId(long id) throws DaoException {
        List<DepositAgreement> depositAgreementList = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_DEPOSITS_BY_USER_ID)){
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                DepositAgreement depositAgreement = DepositAgreement.builder()
                        .id(rs.getLong(1))
                        .agreementNumber(rs.getLong(2))
                        .startDate(LocalDate.parse(rs.getString(3)))
                        .finishDate(LocalDate.parse(rs.getString(4)))
                        .termContract(LocalDate.parse(rs.getString(5)))
                        .depositAmount(rs.getBigDecimal(6))
                        .depositType(DepositAgreement.DepositType.valueOf(rs.getString(7)))
                        .currencyType(DepositAgreement.CurrencyType.valueOf(rs.getString(8)))
                        .client(Client.builder()
                                .id(rs.getLong(9))
                                .name(rs.getString(10))
                                .surname(rs.getString(11))
                                .idNumber(rs.getString(12))
                                .build())
                        .build();
                depositAgreementList.add(depositAgreement);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return depositAgreementList;
    }

    @Override
    public Optional<DepositAgreement> findById(long id) throws DaoException {
        Optional<DepositAgreement> optionalDepositAgreement;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_DEPOSIT_BY_ID)){
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                DepositAgreement depositAgreement = DepositAgreement.builder()
                        .id(rs.getLong(1))
                        .agreementNumber(rs.getLong(2))
                        .startDate(LocalDate.parse(rs.getString(3)))
                        .finishDate(LocalDate.parse(rs.getString(4)))
                        .termContract(LocalDate.parse(rs.getString(5)))
                        .depositAmount(rs.getBigDecimal(6))
                        .depositType(DepositAgreement.DepositType.valueOf(rs.getString(7)))
                        .currencyType(DepositAgreement.CurrencyType.valueOf(rs.getString(8)))
                        .client(Client.builder()
                                .id(rs.getLong(9))
                                .name(rs.getString(10))
                                .surname(rs.getString(11))
                                .patronymic(rs.getString(12))
                                .idNumber(rs.getString(13))
                                .build())
                        .build();
                optionalDepositAgreement = Optional.of(depositAgreement);
            } else {
                optionalDepositAgreement = Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return optionalDepositAgreement;
    }
}
