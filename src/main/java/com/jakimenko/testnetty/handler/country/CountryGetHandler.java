package com.jakimenko.testnetty.handler.country;

import com.jakimenko.testnetty.domain.Country;
import com.jakimenko.testnetty.handler.GetHandler;
import com.jakimenko.testnetty.service.ICountryService;
import com.jakimenko.testnetty.service.impl.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author konst
 */
@Controller
public class CountryGetHandler extends GetHandler<Country> {

    @Autowired
    public CountryGetHandler(ICountryService countryService) {
        super(countryService);
    }
}
