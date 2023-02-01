package com.example.kurs6.model.service;

import com.example.kurs6.enity.DepositBill;
import com.example.kurs6.exception.ServiceException;

import java.util.Map;
import java.util.Optional;

public interface DepositBillService {

    boolean create(DepositBill depositBill) throws ServiceException;
    boolean update(Map<String, String> clientData) throws ServiceException;
    boolean delete(long id) throws ServiceException;
    Optional<DepositBill> findByAgreementNumber(long number) throws ServiceException;
}
