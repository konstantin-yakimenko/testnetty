package com.jakimenko.testnetty.handler.city;

import com.jakimenko.testnetty.domain.City;
import com.jakimenko.testnetty.exception.BadRequestException;
import com.jakimenko.testnetty.handler.UpdateHandler;
import com.jakimenko.testnetty.service.ICityService;
import com.jakimenko.testnetty.service.impl.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * @author konst
 */
@Controller
public class CityUpdateHandler extends UpdateHandler<City> {

    @Autowired
    public CityUpdateHandler(ICityService cityService) {
        super(cityService);
    }

    @Override
    protected City parseJson(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue() == null) {
                throw new BadRequestException();
            }
        }

        City city = new City();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            int hash = entry.getKey().hashCode();
            String value = entry.getValue();

            if ("title".hashCode() == hash) {
                city.setTitle(value);
            }
        }
        return city;
    }
}
