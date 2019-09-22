package com.jakimenko.testnetty.handler;

import com.jakimenko.testnetty.domain.Entity;
import com.jakimenko.testnetty.service.IService;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.Map;

/**
 * @author konst
 */
public abstract class UpdateHandler<T extends Entity> extends EntityHandler<T> {

    public UpdateHandler(IService<T> service) {
        super(service);
    }

    @Override
    public Object handleRequest(FullHttpRequest request) throws Exception {
        int id = fetchId(request.uri());

        Map<String, String> json = readJson(request);
        T model = parseJson(json);
        model.setId(id);

        service.update(model);
        return "{}";
    }
}
