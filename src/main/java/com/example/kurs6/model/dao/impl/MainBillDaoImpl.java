package com.example.kurs6.model.dao.impl;

import com.example.kurs6.enity.DepositBill;
import com.example.kurs6.exception.DaoException;
import com.example.kurs6.model.connection.ConnectionPool;
import com.example.kurs6.model.dao.DepositBillDao;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MainBillDaoImpl implements DepositBillDao {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String SQL_INSERT_MAIN_BILL = "INSERT INTO main_bill (number, bill_amount, deposit_agreement_id, deposit_agreement_number) VALUES (?,?,?,?);";
    private static final String SQL_UPDATE_MAIN_BILL = "UPDATE main_bill SET bill_amount = ? WHERE deposit_agreement_number = ?;";
    private static final String SQL_DELETE_MAIN_BILL = "DELETE FROM main_bill WHERE deposit_agreement_number = ?;";
    private static final String SQL_FIND_ALL_MAIN_BILLS = "SELECT number, bill_amount, deposit_agreement_id, deposit_agreement_number from main_bill;";
    private static final String SQL_FIND_MAIN_BILL_BY_AGREEMENT_NUMBER = "SELECT number, bill_amount, deposit_agreement_id, deposit_agreement_number from main_bill WHERE deposit_agreement_number = ?;";
    private static MainBillDaoImpl INSTANCE;


    public static MainBillDaoImpl getInstance(){
        if (INSTANCE == null){
            INSTANCE = new MainBillDaoImpl();
        }
        return INSTANCE;
    }

    @Override
    public boolean create(DepositBill depositBill) throws DaoException {
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_MAIN_BILL)){
            statement.setLong(1, depositBill.getNumber());
            System.out.println("amount " + depositBill.getBillAmount());
            statement.setBigDecimal(2, depositBill.getBillAmount());
            statement.setLong(3, depositBill.getDepositAgreementId());
            statement.setLong(4, depositBill.getDepositAgreementNumber());
            if(statement.executeUpdate() != 0){
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean update(DepositBill depositBill) throws DaoException {
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_MAIN_BILL)){
            statement.setBigDecimal(1, depositBill.getBillAmount());
            statement.setLong(2, depositBill.getDepositAgreementNumber());
            if(statement.executeUpdate() != 0){
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean delete(DepositBill depositBill) throws DaoException {
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_MAIN_BILL)){
            statement.setLong(1, depositBill.getDepositAgreementNumber());
            if(statement.executeUpdate() != 0){
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<DepositBill> findAll() throws DaoException {
        List<DepositBill> depositBillList = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SQL_FIND_ALL_MAIN_BILLS)){
            while (rs.next()){
                DepositBill depositBill = DepositBill.builder()
                        .number(rs.getLong(1))
                        .billAmount(rs.getBigDecimal(2))
                        .depositAgreementId(rs.getLong(3))
                        .depositAgreementNumber(rs.getLong(4))
                        .build();
                depositBillList.add(depositBill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
        return depositBillList;
    }

    @Override
    public Optional<DepositBill> findByAgreementNumber(long agreementNumber) throws DaoException {
        Optional<DepositBill> optionalDepositBill;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_MAIN_BILL_BY_AGREEMENT_NUMBER)){
            statement.setLong(1, agreementNumber);
            try (ResultSet rs = statement.executeQuery()){
                if(rs.next()){
                    DepositBill depositBill = DepositBill.builder()
                            .number(rs.getLong(1))
                            .billAmount(rs.getBigDecimal(2))
                            .depositAgreementId(rs.getLong(3))
                            .depositAgreementNumber(rs.getLong(4))
                            .build();
                    optionalDepositBill = Optional.of(depositBill);
                } else {
                    optionalDepositBill = Optional.empty();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
        return optionalDepositBill;
    }
}
