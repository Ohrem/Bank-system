package com.example.kurs6.model.service;

import com.example.kurs6.enity.Client;
import com.example.kurs6.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ClientService {

    boolean create(Map<String, String> userData) throws ServiceException;
    boolean updateClientInfo(Map<String, String> clientData) throws ServiceException;
    boolean deleteById(String id) throws ServiceException;
    Optional<Client> findById(String id) throws ServiceException;
    Optional<Client> findByPassportNumber(String number) throws ServiceException;
    List<Client> findAll() throws ServiceException;
}
