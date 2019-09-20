package com.jakimenko.testnetty.domain;

/**
 * @author konst
 */
public class Country {
    private final Long countryId;
    private final String title;

    public Country(Long countryId, String title) {
        this.countryId = countryId;
        this.title = title;
    }

    public Long getCountryId() {
        return countryId;
    }

    public String getTitle() {
        return title;
    }
}
