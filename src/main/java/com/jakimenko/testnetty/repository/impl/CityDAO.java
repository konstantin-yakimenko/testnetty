package com.jakimenko.testnetty.repository.impl;

import com.jakimenko.testnetty.domain.City;
import com.jakimenko.testnetty.exception.NotFoundException;
import com.jakimenko.testnetty.repository.ICityDAO;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author konst
 */
@Repository
public class CityDAO implements ICityDAO {

    private static final ConcurrentMap<Integer, City> map = new ConcurrentHashMap<>();

    @Override
    public City add(City model) {
        map.put(model.getId(), model);
        return model;
    }

    @Override
    public City update(City model) {
        map.put(model.getId(), model);
        return model;
    }

    @Override
    public City findById(int id) throws NotFoundException {
        return map.get(id);
    }

    @Override
    public int count() {
        return map.keySet().size();
    }
}
