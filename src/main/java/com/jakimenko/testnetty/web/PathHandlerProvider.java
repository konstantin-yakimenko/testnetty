package com.jakimenko.testnetty.web;

import com.jakimenko.testnetty.annotation.RequestMapping;
import io.netty.handler.codec.http.FullHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;

/**
 * @author konst
 */
@Component
public class PathHandlerProvider {

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
}
