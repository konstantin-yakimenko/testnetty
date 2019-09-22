package com.jakimenko.testnetty.domain;

import java.util.List;

/**
 * @author konst
 */
public class Country {
    private Long countryId;
    private String title;
    private List<City> cities;

    public Country(Long countryId, String title, List<City> cities) {
        this.countryId = countryId;
        this.title = title;
        this.cities = cities;
    }

    public Long getCountryId() {
        return countryId;
    }

    public String getTitle() {
        return title;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "Country{" +
            "countryId=" + countryId +
            ", title='" + title + '\'' +
            ", cities=" + cities +
            '}';
    }
}
