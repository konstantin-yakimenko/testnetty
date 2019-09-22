package com.jakimenko.testnetty.handler.country;

import com.jakimenko.testnetty.domain.Country;
import com.jakimenko.testnetty.exception.BadRequestException;
import com.jakimenko.testnetty.handler.UpdateHandler;
import com.jakimenko.testnetty.service.ICountryService;
import com.jakimenko.testnetty.service.impl.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * @author konst
 */
@Controller
public class CountryUpdateHandler extends UpdateHandler<Country> {

    @Autowired
    public CountryUpdateHandler(ICountryService countryService) {
        super(countryService);
    }


    @Override
    protected Country parseJson(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue() == null) {
                throw new BadRequestException();
            }
        }

        Country country = new Country();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            int hash = entry.getKey().hashCode();
            String value = entry.getValue();

            if ("title".hashCode() == hash) {
                country.setTitle(value);
            }
        }
        return country;
    }
}