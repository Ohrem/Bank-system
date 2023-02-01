package com.example.kurs6.model.service.impl;

import com.example.kurs6.command.ParameterAndAttribute;
import com.example.kurs6.enity.BankBill;
import com.example.kurs6.enity.DepositAgreement;
import com.example.kurs6.enity.DepositBill;
import com.example.kurs6.exception.DaoException;
import com.example.kurs6.exception.ServiceException;

import com.example.kurs6.model.dao.impl.DepositAgreementDaoImpl;
import com.example.kurs6.model.dao.impl.MainBillDaoImpl;
import com.example.kurs6.model.dao.impl.ServiceBillDaoImpl;
import com.example.kurs6.model.service.DepositAgreementService;
import com.example.kurs6.util.DepositAgreementExtractor;
import com.example.kurs6.util.IdGenerator;
import com.example.kurs6.validator.AgreementValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;



public class DepositAgreementServiceImpl implements DepositAgreementService {

    private static final DepositAgreementDaoImpl depositAgreementDao = DepositAgreementDaoImpl.getInstance();
    private static final MainBillDaoImpl mainBillDao = MainBillDaoImpl.getInstance();
    private static final ServiceBillDaoImpl serviceBillDao = ServiceBillDaoImpl.getInstance();

    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<DepositAgreement> findAll() throws ServiceException {
        List<DepositAgreement> depositAgreements = new ArrayList<>();
        try {
            depositAgreements = depositAgreementDao.findAll();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return depositAgreements;
    }

    @Override
    public boolean create(Map<String, String> userData) throws ServiceException {
        boolean result = false;
        DepositAgreement depositAgreement = DepositAgreementExtractor.depositExtractor(userData);
        System.out.println(depositAgreement);
        List<Long> generatedNumberList = IdGenerator.generateDepositAgreementNumber();

        try {
            Long generatedAgreementId = depositAgreementDao.createAndReturnId(depositAgreement);
            DepositBill mainBill = DepositBill.builder()
                    .number(generatedNumberList.get(0))
                    .billAmount(depositAgreement.getDepositAmount())
                    .depositAgreementId(generatedAgreementId)
                    .depositAgreementNumber(depositAgreement.getAgreementNumber())
                    .build();
            DepositBill serviceScore = DepositBill.builder()
                    .number(generatedNumberList.get(1))
                    .depositAgreementId(generatedAgreementId)
                    .depositAgreementNumber(depositAgreement.getAgreementNumber())
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
    public boolean update(long id, Map<String, String> depositData) throws ServiceException {
        String agreementNumber = depositData.get(ParameterAndAttribute.AGREEMENT_NUMBER);
        String startDate = depositData.get(ParameterAndAttribute.START_DATE);
        String finishDate = depositData.get(ParameterAndAttribute.FINISH_DATE);
        String termContract = depositData.get(ParameterAndAttribute.TERM_CONTRACT);
        String depositAmount = depositData.get(ParameterAndAttribute.DEPOSIT_AMOUNT);
        String depositType = depositData.get(ParameterAndAttribute.DEPOSIT_TYPE);
        String currencyType = depositData.get(ParameterAndAttribute.CURRENCY_TYPE);

        boolean isUpdated = false;

        try {
            Optional<DepositAgreement> optionalDepositAgreement = depositAgreementDao.findById(id);
            if (optionalDepositAgreement.isPresent()) {
                System.out.println("deposit found");
                DepositAgreement depositAgreement = DepositAgreement.builder()
                        .agreementNumber(Long.parseLong(agreementNumber))
                        .startDate(LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE))
                        .finishDate(LocalDate.parse(finishDate, DateTimeFormatter.ISO_DATE))
                        .termContract(LocalDate.parse(termContract, DateTimeFormatter.ISO_DATE))
                        .depositAmount(new BigDecimal(depositAmount))
                        .depositType(DepositAgreement.DepositType.valueOf(depositType))
                        .currencyType(DepositAgreement.CurrencyType.valueOf(currencyType))
                        .build();
                System.out.println(depositAgreement.toString());
                isUpdated = depositAgreementDao.update(depositAgreement);
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
            Optional<DepositAgreement> optionalDepositAgreement = depositAgreementDao.findById(id);
            if (optionalDepositAgreement.isPresent()) {
                DepositAgreement agreement = optionalDepositAgreement.get();
                depositDeleted = depositAgreementDao.delete(agreement);
            } else {
                depositDeleted = false;
            }
        } catch (DaoException e) {
            throw new ServiceException("Service exception", e);
        }
        return depositDeleted;
    }

    @Override
    public Optional<DepositAgreement> findById(long id) throws ServiceException {
        Optional<DepositAgreement> optionalDepositAgreement;
        try {
            optionalDepositAgreement = depositAgreementDao.findById(id);
        } catch (DaoException e) {
            logger.error("Service exception trying Agreement by id", e);
            throw new ServiceException("Service exception", e);
        }
        return optionalDepositAgreement;
    }

    @Override
    public List<DepositAgreement> findByUserId(long userId) throws ServiceException {
        List<DepositAgreement> depositAgreementList;
        try {
            depositAgreementList = depositAgreementDao.findByUserId(userId);
        } catch (DaoException e) {
            logger.error("Service exception trying Agreement  by id", e);
            throw new ServiceException("Service exception", e);
        }
        return depositAgreementList;
    }


}
