package com.jakimenko.testnetty.repository;

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
        countries.add(new Country(1L, "Russia", ImmutableList.of(new City(1L, "Moscow"))));
        countries.add(new Country(2L, "USA", ImmutableList.of(new City(2L, "Los Angeles"))));
        countries.add(new Country(3L, "Great Britan", ImmutableList.of(new City(3L, "London"))));
        return countries;
    }
}
