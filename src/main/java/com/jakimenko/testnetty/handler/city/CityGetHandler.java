package com.jakimenko.testnetty.handler.city;

import com.jakimenko.testnetty.domain.City;
import com.jakimenko.testnetty.handler.GetHandler;
import com.jakimenko.testnetty.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author konst
 */
@Controller
public class CityGetHandler extends GetHandler<City> {

    @Autowired
    public CityGetHandler(ICityService cityService) {
        super(cityService);
    }
}
