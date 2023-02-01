package com.example.kurs6.model.service;

import com.example.kurs6.enity.DepositAgreement;
import com.example.kurs6.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DepositAgreementService {

    List<DepositAgreement> findAll() throws ServiceException;
    boolean create(Map<String, String> userData) throws ServiceException;
    boolean update(long id,Map<String, String> updatedData) throws ServiceException ;
    boolean delete(long id) throws ServiceException ;
    Optional<DepositAgreement> findById(long number) throws ServiceException ;

    List<DepositAgreement> findByUserId(long idNumber) throws ServiceException ;
}
