package com.example.kurs6.model.dao;

import com.example.kurs6.enity.DepositAgreement;
import com.example.kurs6.exception.DaoException;

import java.util.Optional;

public interface DepositAgreementDao extends BaseDao<DepositAgreement> {

    Optional<DepositAgreement> findById(long id) throws DaoException;
}
