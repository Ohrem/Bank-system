package com.example.kurs6.model.dao;

import com.example.kurs6.enity.DepositBill;
import com.example.kurs6.exception.DaoException;

import java.util.Optional;

public interface DepositBillDao extends BaseDao<DepositBill> {
    Optional<DepositBill> findByAgreementNumber(long agreementNumber) throws DaoException;
}