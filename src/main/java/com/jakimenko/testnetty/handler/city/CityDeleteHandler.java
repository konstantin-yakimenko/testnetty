package com.jakimenko.testnetty.handler.city;

import com.jakimenko.testnetty.domain.City;
import com.jakimenko.testnetty.handler.DeleteHandler;
import com.jakimenko.testnetty.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author konst
 */
@Controller
public class CityDeleteHandler extends DeleteHandler<City> {

    @Autowired
    public CityDeleteHandler(ICityService cityService) {
        super(cityService);
    }
}
