package com.jakimenko.testnetty.controller;

import com.jakimenko.testnetty.annotation.RequestMapping;
import com.jakimenko.testnetty.domain.Country;
import com.jakimenko.testnetty.repository.impl.CountryRepository;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author konst
 */
@Controller
public class CountryController {
    private static final Logger logger = LoggerFactory.getLogger(CountryController.class);

    private final CountryRepository countryRepository;

    @Autowired
    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @RequestMapping("/api/countries")
    public Object getCountry(FullHttpRequest request) {
        String uri = request.uri();
        logger.info("get countries: {}", uri);
        if (uri.equalsIgnoreCase("/api/countries")) {
            return countryRepository.getAll();
        }
        String id = uri.replace("/api/countries/", "");
        long countryId = Long.parseLong(id);

        for (Country country : countryRepository.getAll()) {
            if (country.getId() == countryId) {
                return country;
            }
        }
        return "";
    }
}
