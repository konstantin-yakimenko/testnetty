package com.jakimenko.testnetty.handler.country;

import com.jakimenko.testnetty.domain.Country;
import com.jakimenko.testnetty.handler.DeleteHandler;
import com.jakimenko.testnetty.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author konst
 */
@Controller
public class CountryDeleteHandler extends DeleteHandler<Country> {

    @Autowired
    public CountryDeleteHandler(ICountryService countryService) {
        super(countryService);
    }
}
