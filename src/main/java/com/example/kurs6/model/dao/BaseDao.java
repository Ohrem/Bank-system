package com.example.kurs6.model.dao;

import com.example.kurs6.enity.AbstractEntity;
import com.example.kurs6.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T extends AbstractEntity> {

    boolean create(T t) throws DaoException;
    boolean update(T t) throws DaoException;
    boolean delete(T t) throws DaoException;
    List<T> findAll() throws DaoException;

}
