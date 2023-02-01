package com.example.kurs6.model.dao;

import com.example.kurs6.enity.BankBill;
import com.example.kurs6.exception.DaoException;

import java.util.Optional;

public interface BankBillDao extends BaseDao<BankBill>{

    Optional<BankBill> findById(long id) throws DaoException;
}
