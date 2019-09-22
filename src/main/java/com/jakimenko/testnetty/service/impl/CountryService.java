package com.jakimenko.testnetty.service.impl;

import com.jakimenko.testnetty.domain.Country;
import com.jakimenko.testnetty.exception.NotFoundException;
import com.jakimenko.testnetty.repository.impl.CountryDAO;
import com.jakimenko.testnetty.repository.ICountryDAO;
import com.jakimenko.testnetty.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author konst
 */
@Service
public class CountryService extends AbstractService<Country, ICountryDAO> implements ICountryService {

    @Autowired
    public CountryService(ICountryDAO countryDAO) {
        super(countryDAO);
    }

    @Override
    public Country update(Country model) {
        Country country = dao.findById(model.getId());
        if (country == null) {
            throw new NotFoundException();
        }

        Country countryUser = new Country(
            country.getId(),
            model.getTitle() != null ? model.getTitle() : country.getTitle()
        );
        return dao.update(countryUser);
    }
}
