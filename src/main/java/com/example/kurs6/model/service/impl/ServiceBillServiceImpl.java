package com.example.kurs6.model.service.impl;

import com.example.kurs6.command.ParameterAndAttribute;
import com.example.kurs6.enity.DepositBill;
import com.example.kurs6.exception.DaoException;
import com.example.kurs6.exception.ServiceException;
import com.example.kurs6.model.dao.impl.MainBillDaoImpl;
import com.example.kurs6.model.dao.impl.ServiceBillDaoImpl;
import com.example.kurs6.model.service.DepositBillService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ServiceBillServiceImpl implements DepositBillService {

    private final static ServiceBillDaoImpl billDao = ServiceBillDaoImpl.getInstance();

    @Override
    public boolean create(DepositBill depositBill) throws ServiceException {
        boolean isCreated;
        try {
            isCreated = billDao.create(depositBill);
        } catch (DaoException e) {
            throw new ServiceException("Service exception",e);
        }
        return isCreated;
    }

    @Override
    public boolean update(Map<String, String> clientData) throws ServiceException {
        boolean isUpdated;
        DepositBill depositBill = DepositBill.builder()
                .billAmount(new BigDecimal(clientData.get(ParameterAndAttribute.BILL_AMOUNT)))
                .depositAgreementNumber(Long.valueOf(clientData.get(ParameterAndAttribute.DEPOSIT_AGREEMENT_NUMBER)))
                .build();
        try {
            isUpdated = billDao.update(depositBill);
        } catch (DaoException e) {
            throw new ServiceException("Service exception",e);
        }
        return isUpdated;
    }

    @Override
    public boolean delete(long agreementNumber) throws ServiceException {
        boolean isDeleted;
        try {
            Optional<DepositBill> optionalDepositBill = billDao.findByAgreementNumber(agreementNumber);
            if (optionalDepositBill.isPresent()) {
                DepositBill depositBill = optionalDepositBill.get();
                isDeleted = billDao.delete(depositBill);
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
            optionalDepositBill = billDao.findByAgreementNumber(number);
        } catch (DaoException e) {
            throw new ServiceException("Service exception",e);
        }
        return optionalDepositBill;
    }
}
