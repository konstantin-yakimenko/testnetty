package com.jakimenko.testnetty.repository;

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
        countries.add(new Country(1L, "Russia"));
        countries.add(new Country(2L, "USA"));
        countries.add(new Country(3L, "Great Britan"));
        return countries;
    }
}
