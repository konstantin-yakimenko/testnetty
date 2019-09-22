package com.jakimenko.testnetty.service.impl;

import com.jakimenko.testnetty.domain.City;
import com.jakimenko.testnetty.exception.NotFoundException;
import com.jakimenko.testnetty.repository.impl.CityDAO;
import com.jakimenko.testnetty.repository.ICityDAO;
import com.jakimenko.testnetty.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author konst
 */
@Service
public class CityService extends AbstractService<City, ICityDAO> implements ICityService {

    @Autowired
    public CityService(ICityDAO cityDAO) {
        super(cityDAO);
    }

    @Override
    public City update(City model) {
        City city = dao.findById(model.getId());
        if (city == null) {
            throw new NotFoundException();
        }

        City cityUser = new City(
            city.getId(),
            model.getTitle() != null ? model.getTitle() : city.getTitle()
        );
        return dao.update(cityUser);
    }
}
