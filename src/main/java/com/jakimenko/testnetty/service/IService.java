package com.jakimenko.testnetty.service;

import com.jakimenko.testnetty.domain.Entity;

/**
 * @author konst
 */
public interface IService<T extends Entity> {

    T findById(int id);

    T add(T model);

    int count();

    T update(T model);

}
