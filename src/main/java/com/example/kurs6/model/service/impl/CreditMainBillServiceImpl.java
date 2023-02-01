package com.example.kurs6.model.service.impl;

import com.example.kurs6.command.ParameterAndAttribute;
import com.example.kurs6.enity.DepositBill;
import com.example.kurs6.exception.DaoException;
import com.example.kurs6.exception.ServiceException;
import com.example.kurs6.model.dao.impl.CreditMainBillDaoImpl;
import com.example.kurs6.model.service.DepositBillService;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class CreditMainBillServiceImpl implements DepositBillService {
    private static final CreditMainBillDaoImpl mainBillDao = CreditMainBillDaoImpl.getInstance();

    @Override
    public boolean create(DepositBill depositBill) throws ServiceException {
        boolean isCreated;
        try {
            isCreated = mainBillDao.create(depositBill);
        } catch (DaoException e) {
            throw new ServiceException("Service exception",e);
        }
        return isCreated;
    }

    @Override
    public boolean update(Map<String, String> clientData) throws ServiceException {
        boolean isUpdated;
        DepositBill depositBill = DepositBill.builder()
                .number(Long.valueOf(clientData.get(ParameterAndAttribute.NUMBER)))
                .billAmount(new BigDecimal(clientData.get(ParameterAndAttribute.BILL_AMOUNT)))
                .depositAgreementId(Long.valueOf(clientData.get(ParameterAndAttribute.DEPOSIT_ID)))
                .depositAgreementNumber(Long.valueOf(clientData.get(ParameterAndAttribute.DEPOSIT_AGREEMENT_NUMBER)))
                .build();
        try {
            isUpdated = mainBillDao.update(depositBill);
        } catch (DaoException e) {
            throw new ServiceException("Service exception",e);
        }
        return isUpdated;
    }

    @Override
    public boolean delete(long agreementNumber) throws ServiceException {
        boolean isDeleted;
        try {
            Optional<DepositBill> optionalDepositBill = mainBillDao.findByAgreementNumber(agreementNumber);
            if (optionalDepositBill.isPresent()) {
                DepositBill depositBill = optionalDepositBill.get();
                isDeleted = mainBillDao.delete(depositBill);
            } else {
                isDeleted = false;
            }
        } catch (DaoException e) {
            throw new ServiceException("Service exception",e);
        }
        return isDeleted;
    }

    @Override
    public Optional<DepositBill> findByAgreementNumber(long number) throws ServiceException {
        Optional<DepositBill> optionalDepositBill;
        try {
            optionalDepositBill = mainBillDao.findByAgreementNumber(number);
        } catch (DaoException e) {
            throw new ServiceException("Service exception",e);
        }
        return optionalDepositBill;
    }
}
