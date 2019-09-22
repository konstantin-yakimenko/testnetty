package com.jakimenko.testnetty.service.impl;

import com.jakimenko.testnetty.domain.Entity;
import com.jakimenko.testnetty.exception.BadRequestException;
import com.jakimenko.testnetty.exception.NotFoundException;
import com.jakimenko.testnetty.repository.IDao;
import com.jakimenko.testnetty.service.IService;

/**
 * @author konst
 */
public abstract class AbstractService<T extends Entity, R extends IDao<T>> implements IService<T> {

    protected final R dao;

    public AbstractService(R dao) {
        this.dao = dao;
    }

    @Override
    public T findById(int id) {
        T model = dao.findById(id);
        if (model == null) {
            throw new NotFoundException();
        }
        return model;
    }

    @Override
    public T add(T model) {
        if (dao.findById(model.getId()) != null) {
            throw new BadRequestException();
        }

        return dao.add(model);
    }

    @Override
    public int count() {
        return dao.count();
    }

}
