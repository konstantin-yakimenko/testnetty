package com.jakimenko.testnetty.repository.impl;

import com.jakimenko.testnetty.domain.Country;
import com.jakimenko.testnetty.exception.NotFoundException;
import com.jakimenko.testnetty.repository.ICountryDAO;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author konst
 */
@Repository
public class CountryDAO implements ICountryDAO {

    private static final ConcurrentMap<Integer, Country> map = new ConcurrentHashMap<>();
    private static final AtomicInteger idGenerator = new AtomicInteger();

    @Override
    public Country add(Country model) {
        int id = idGenerator.incrementAndGet();
        model.setId(id);
        map.put(id, model);
        return model;
//        return new Country(1, "Russia");
    }

    @Override
    public Country update(Country model) {
        map.put(model.getId(), model);
        return model;
//        return new Country(1, "Russia");
    }

    @Override
    public Country findById(int id) throws NotFoundException {
        return map.get(id);
//        return new Country(1, "Russia");
    }

    @Override
    public int count() {
        return map.keySet().size();
//        return 1;
    }

    @Override
    public Country delete(int id) {
        Country country = map.get(id);
        map.remove(id);
        return country;
//        return new Country(1, "Russia");
    }
}
