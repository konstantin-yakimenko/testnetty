package com.jakimenko.testnetty.repository.impl;

import com.google.common.collect.ImmutableList;
import com.jakimenko.testnetty.domain.City;
import com.jakimenko.testnetty.domain.Country;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author konst
 */
@Repository
public class CountryRepository {

    public List<Country> getAll() {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country(1, "Russia"));
        countries.add(new Country(2, "USA"));
        countries.add(new Country(3, "Great Britan"));
        return countries;
    }
}
