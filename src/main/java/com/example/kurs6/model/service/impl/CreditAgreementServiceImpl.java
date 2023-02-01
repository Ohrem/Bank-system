package com.example.kurs6.model.service.impl;

import com.example.kurs6.command.ParameterAndAttribute;
import com.example.kurs6.enity.DepositAgreement;
import com.example.kurs6.enity.DepositBill;
import com.example.kurs6.exception.DaoException;
import com.example.kurs6.exception.ServiceException;
import com.example.kurs6.model.dao.impl.*;
import com.example.kurs6.model.service.DepositAgreementService;
import com.example.kurs6.util.DepositAgreementExtractor;
import com.example.kurs6.util.IdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CreditAgreementServiceImpl implements DepositAgreementService {
    private static final CreditAgreementDaoImpl creditAgreementDao = CreditAgreementDaoImpl.getInstance();
    private static final CreditMainBillDaoImpl mainBillDao = CreditMainBillDaoImpl.getInstance();
    private static final CreditServiceBillDaoImpl serviceBillDao = CreditServiceBillDaoImpl.getInstance();
    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<DepositAgreement> findAll() throws ServiceException {
        List<DepositAgreement> depositAgreements = new ArrayList<>();
        try {
            depositAgreements = creditAgreementDao.findAll();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return depositAgreements;
    }

    @Override
    public boolean create(Map<String, String> userData) throws ServiceException {
        boolean result = false;
        DepositAgreement creditAgreement = DepositAgreementExtractor.creditExtractor(userData);
        System.out.println(creditAgreement);
        List<Long> generatedNumberList = IdGenerator.generateDepositAgreementNumber();

        try {
            Long generatedAgreementId = creditAgreementDao.createAndReturnId(creditAgreement);
            DepositBill mainBill = DepositBill.builder()
                    .number(generatedNumberList.get(0))
                    .billAmount(creditAgreement.getDepositAmount())
                    .depositAgreementId(generatedAgreementId)
                    .depositAgreementNumber(creditAgreement.getAgreementNumber())
                    .build();
            DepositBill serviceScore = DepositBill.builder()
                    .number(generatedNumberList.get(1))
                    .depositAgreementId(generatedAgreementId)
                    .depositAgreementNumber(creditAgreement.getAgreementNumber())
                    .build();
            if (mainBillDao.create(mainBill) && serviceBillDao.create(serviceScore)){
                result = true;
            }
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public boolean update(long id, Map<String, String> updatedData) throws ServiceException {
        String agreementNumber = updatedData.get(ParameterAndAttribute.AGREEMENT_NUMBER);
        String startDate = updatedData.get(ParameterAndAttribute.START_DATE);
        String finishDate = updatedData.get(ParameterAndAttribute.FINISH_DATE);
        String termContract = updatedData.get(ParameterAndAttribute.TERM_CONTRACT);
        String depositAmount = updatedData.get(ParameterAndAttribute.DEPOSIT_AMOUNT);
        String creditType = updatedData.get(ParameterAndAttribute.CREDIT_TYPE);
        String currencyType = updatedData.get(ParameterAndAttribute.CURRENCY_TYPE);

        boolean isUpdated = false;

        try {
            Optional<DepositAgreement> optionalDepositAgreement = creditAgreementDao.findById(id);
            if (optionalDepositAgreement.isPresent()) {
                System.out.println("deposit found");
                DepositAgreement depositAgreement = DepositAgreement.builder()
                        .agreementNumber(Long.parseLong(agreementNumber))
                        .startDate(LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE))
                        .finishDate(LocalDate.parse(finishDate, DateTimeFormatter.ISO_DATE))
                        .termContract(LocalDate.parse(termContract, DateTimeFormatter.ISO_DATE))
                        .depositAmount(new BigDecimal(depositAmount))
                        .creditType(DepositAgreement.CreditType.valueOf(creditType))
                        .currencyType(DepositAgreement.CurrencyType.valueOf(currencyType))
                        .build();
                System.out.println(depositAgreement.toString());
                isUpdated = creditAgreementDao.update(depositAgreement);
            }
        } catch (DaoException e) {
            throw new ServiceException("Service exception", e);
        }
        return isUpdated;
    }

    @Override
    public boolean delete(long id) throws ServiceException {
        boolean depositDeleted;
        try {
            Optional<DepositAgreement> optionalDepositAgreement = creditAgreementDao.findById(id);
            if (optionalDepositAgreement.isPresent()) {
                DepositAgreement agreement = optionalDepositAgreement.get();
                depositDeleted = creditAgreementDao.delete(agreement);
            } else {
                depositDeleted = false;
            }
        } catch (DaoException e) {
            throw new ServiceException("Service exception", e);
        }
        return depositDeleted;
    }

    @Override
    public Optional<DepositAgreement> findById(long number) throws ServiceException {
        Optional<DepositAgreement> optionalDepositAgreement;
        try {
            optionalDepositAgreement = creditAgreementDao.findById(number);
        } catch (DaoException e) {
            logger.error("Service exception trying Agreement by id", e);
            throw new ServiceException("Service exception", e);
        }
        return optionalDepositAgreement;
    }

    @Override
    public List<DepositAgreement> findByUserId(long idNumber) throws ServiceException {
        List<DepositAgreement> depositAgreementList;
        try {
            depositAgreementList = creditAgreementDao.findByUserId(idNumber);
        } catch (DaoException e) {
            logger.error("Service exception trying Agreement  by id", e);
            throw new ServiceException("Service exception", e);
        }
        return depositAgreementList;
    }
}
