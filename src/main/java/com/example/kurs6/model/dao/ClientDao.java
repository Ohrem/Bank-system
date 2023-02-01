package com.example.kurs6.model.dao;

import com.example.kurs6.enity.Client;
import com.example.kurs6.exception.DaoException;
import com.example.kurs6.model.dao.BaseDao;

import java.util.Optional;

public interface ClientDao extends BaseDao<Client> {

    Optional<Client> findById(String id) throws DaoException;
}
