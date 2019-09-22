package com.jakimenko.testnetty.repository.impl;

import com.jakimenko.testnetty.domain.Country;
import com.jakimenko.testnetty.exception.NotFoundException;
import com.jakimenko.testnetty.repository.ICountryDAO;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author konst
 */
@Repository
public class CountryDAO implements ICountryDAO {

    private static final ConcurrentMap<Integer, Country> map = new ConcurrentHashMap<>();

    @Override
    public Country add(Country model) {
        map.put(model.getId(), model);
        return model;
    }

    @Override
    public Country update(Country model) {
        map.put(model.getId(), model);
        return model;
    }

    @Override
    public Country findById(int id) throws NotFoundException {
        return map.get(id);
    }

    @Override
    public int count() {
        return map.keySet().size();
    }
}
