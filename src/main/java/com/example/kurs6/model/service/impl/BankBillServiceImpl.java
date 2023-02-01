package com.example.kurs6.model.service.impl;

import com.example.kurs6.command.ParameterAndAttribute;
import com.example.kurs6.enity.BankBill;
import com.example.kurs6.exception.DaoException;
import com.example.kurs6.exception.ServiceException;
import com.example.kurs6.model.dao.BankBillDao;
import com.example.kurs6.model.dao.impl.BankBillDaoImpl;
import com.example.kurs6.model.service.BankBillService;
import com.example.kurs6.model.service.BaseService;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class BankBillServiceImpl implements BaseService<BankBill> {

    private static final BankBillDaoImpl bankBillDao = BankBillDaoImpl.getInstance();
    public static final Long BANK_BILL_NUMBER = 3016594966941L;



    @Override
    public boolean create(Map<String, String> data) throws ServiceException {

        boolean isCreated;
        String balance = data.get(ParameterAndAttribute.BALANCE);
        String billNumber = data.get(ParameterAndAttribute.BILL_NUMBER);
        BankBill bankBill = BankBill.builder()
                .balance(new BigDecimal(balance))
                .billNumber(Long.valueOf(billNumber))
                .build();

        try {
            isCreated = bankBillDao.create(bankBill);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }

        return isCreated;
    }

    @Override
    public boolean update(String id, Map<String, String> data) throws ServiceException {

        boolean isUpdated;
        String balance = data.get(ParameterAndAttribute.BALANCE);
        String billNumber = data.get(ParameterAndAttribute.BILL_NUMBER);
        BankBill bankBill = BankBill.builder()
                .id(Long.parseLong(id))
                .balance(new BigDecimal(balance))
                .billNumber(Long.valueOf(billNumber))
                .build();
        try {
            isUpdated = bankBillDao.update(bankBill);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }

        return isUpdated;
    }

    @Override
    public boolean delete(String id) throws ServiceException {
        return false;
    }

    @Override
    public Optional<BankBill> findById(String id) throws ServiceException {
        Optional<BankBill> optionalBankBill;
        try {
            optionalBankBill = bankBillDao.findById(Long.parseLong(id));
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
        return optionalBankBill;
    }
}
