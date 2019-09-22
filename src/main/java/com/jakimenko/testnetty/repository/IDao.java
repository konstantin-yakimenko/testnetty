package com.jakimenko.testnetty.repository;

import com.jakimenko.testnetty.domain.Entity;
import com.jakimenko.testnetty.exception.NotFoundException;

/**
 * @author konst
 */
public interface IDao<T extends Entity> {

    T add(T model);

    T update(T model);

    T findById(int id) throws NotFoundException;

    int count();

}
