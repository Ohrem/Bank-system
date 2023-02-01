package com.example.kurs6.model.dao.impl;

import com.example.kurs6.enity.BankBill;
import com.example.kurs6.exception.DaoException;
import com.example.kurs6.model.connection.ConnectionPool;
import com.example.kurs6.model.dao.BankBillDao;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BankBillDaoImpl implements BankBillDao {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String SQL_UPDATE_BANK_BILL = "UPDATE bank_bill SET balance = ? WHERE id = ?;";
    private static final String SQL_FIND_BANK_BILL = "SELECT id, balance, bill_number from bank_bill where id = ?;";
    private static BankBillDaoImpl INSTANCE;


    public static BankBillDaoImpl getInstance(){
        if (INSTANCE == null){
            INSTANCE = new BankBillDaoImpl();
        }
        return INSTANCE;
    }


    @Override
    public boolean create(BankBill bankBill) throws DaoException {
        return false;
    }

    @Override
    public boolean update(BankBill bankBill) throws DaoException {
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_BANK_BILL)) {
            statement.setBigDecimal(1, bankBill.getBalance());
            statement.setLong(2, bankBill.getId());
            if (statement.executeUpdate() != 0){
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean delete(BankBill bankBill) throws DaoException {
        return false;
    }

    @Override
    public List<BankBill> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<BankBill> findById(long id) throws DaoException {
        Optional<BankBill> optionalBankBill;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_BANK_BILL)) {
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()){
                if (rs.next()){
                    BankBill bankBill = BankBill.builder()
                            .id(rs.getLong(1))
                            .balance(rs.getBigDecimal(2))
                            .billNumber(rs.getLong(3))
                            .build();
                    optionalBankBill = Optional.of(bankBill);
                } else {
                    optionalBankBill = Optional.empty();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
        return optionalBankBill;
    }
}
