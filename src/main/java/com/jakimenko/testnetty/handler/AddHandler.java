package com.jakimenko.testnetty.handler;

import com.jakimenko.testnetty.domain.Entity;
import com.jakimenko.testnetty.service.IService;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.Map;

/**
 * @author konst
 */
public abstract class AddHandler<T extends Entity> extends EntityHandler<T> {

    public AddHandler(IService<T> service) {
        super(service);
    }

    @Override
    public Object handleRequest(FullHttpRequest request) throws Exception {
        Map<String, String> json = readJson(request);
        T model = parseJson(json);
        return service.add(model);
    }
}
