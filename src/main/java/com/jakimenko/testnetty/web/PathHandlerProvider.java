package com.jakimenko.testnetty.web;

import com.jakimenko.testnetty.handler.IHttpHandler;
import com.jakimenko.testnetty.handler.city.CityAddHandler;
import com.jakimenko.testnetty.handler.city.CityGetHandler;
import com.jakimenko.testnetty.handler.city.CityUpdateHandler;
import com.jakimenko.testnetty.handler.country.CountryAddHandler;
import com.jakimenko.testnetty.handler.country.CountryGetHandler;
import com.jakimenko.testnetty.handler.country.CountryUpdateHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author konst
 */
@Component
public class PathHandlerProvider {

    private static final Logger logger = LoggerFactory.getLogger(PathHandlerProvider.class);

    @Autowired
    public PathHandlerProvider(ApplicationContext context) {
        this.CITY_GET_HANDLER = context.getBean(CityGetHandler.class);
        this.CITY_ADD_HANDLER = context.getBean(CityAddHandler.class);
        this.CITY_UPDATE_HANDLER = context.getBean(CityUpdateHandler.class);

        this.COUNTRY_GET_HANDLER = context.getBean(CountryGetHandler.class);
        this.COUNTRY_ADD_HANDLER = context.getBean(CountryAddHandler.class);
        this.COUNTRY_UPDATE_HANDLER = context.getBean(CountryUpdateHandler.class);
    }

    private final CityGetHandler CITY_GET_HANDLER;
    private final CityAddHandler CITY_ADD_HANDLER;
    private final CityUpdateHandler CITY_UPDATE_HANDLER;

    private final CountryGetHandler COUNTRY_GET_HANDLER;
    private final CountryAddHandler COUNTRY_ADD_HANDLER;
    private final CountryUpdateHandler COUNTRY_UPDATE_HANDLER;

    public IHttpHandler getHandler(FullHttpRequest request) {
        String uri = request.uri();
        logger.info("Request: {}", uri);
        if (uri.contains("?")) {
            uri = uri.substring(0, uri.indexOf("?"));
        }

        HttpMethod method = request.method();
        if (method.equals(HttpMethod.POST)) {
            if (uri.startsWith("/api/city")) {
                return CITY_ADD_HANDLER;
            }
            if (uri.startsWith("/api/country")) {
                return COUNTRY_ADD_HANDLER;
            }
        } else if (method.equals(HttpMethod.PUT)) {
            if (uri.startsWith("/api/city")) {
                return CITY_UPDATE_HANDLER;
            }
            if (uri.startsWith("/api/country")) {
                return COUNTRY_UPDATE_HANDLER;
            }
        } else if (method.equals(HttpMethod.GET)) {
            if (uri.startsWith("/api/city")) {
                return CITY_GET_HANDLER;
            }

            if (uri.startsWith("/api/country")) {
                return COUNTRY_GET_HANDLER;
            }
        }
        logger.error("Not found handler for request: {}", uri);
        return null;
    }
}
/*
    private ApplicationContext context;
    private Map<String, Function<FullHttpRequest, ?>> storage = new TreeMap<>();

    @Autowired
    public PathHandlerProvider(ApplicationContext context) {
        this.context = context;
    }

    @PostConstruct
    private void init() {
        Collection<Object> beans = context.getBeansWithAnnotation(Controller.class).values();
        for (Object bean : beans) {
            Method[] funtions = bean.getClass().getDeclaredMethods();

            for (Method funtion : funtions) {
                if (!funtion.isAnnotationPresent(RequestMapping.class)) {
                    continue;
                }
                RequestMapping pathAnnotation = funtion.getAnnotation(RequestMapping.class);
                String path = pathAnnotation.value();
                Parameter[] parameters = funtion.getParameters();
                Parameter parameter = parameters[0];
//                if (parameters.length > 1 || !FullHttpRequest.class.isInstance(parameter)) {
//                    throw new IllegalStateException("Incorrect parameter type of " + parameter);
//                }
                if (storage.containsKey(pathAnnotation.value())) {
                    throw new IllegalStateException("Mapping $path is already exists.");
                } else {
                    storage.put(path, request -> {
                        try {
                            return funtion.invoke(bean, request);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                            return null;
                        }
                    });
                }
            }
        }
    }

    public Function<FullHttpRequest, ?> getHandler(FullHttpRequest request) {
        for (Map.Entry<String, Function<FullHttpRequest, ?>> entry : storage.entrySet()) {
            if (request.uri().startsWith(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

 */