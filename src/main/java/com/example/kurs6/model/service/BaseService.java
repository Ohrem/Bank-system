package com.example.kurs6.model.service;

import com.example.kurs6.enity.Client;
import com.example.kurs6.exception.ServiceException;

import java.util.Map;
import java.util.Optional;

public interface BaseService<T> {

    boolean create(Map<String, String> data) throws ServiceException ;
    boolean update(String id, Map<String, String> data) throws ServiceException ;
    boolean delete(String id) throws ServiceException ;
    Optional<T> findById(String id) throws ServiceException;
}
