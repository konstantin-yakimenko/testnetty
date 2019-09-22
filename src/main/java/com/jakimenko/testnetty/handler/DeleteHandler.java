package com.jakimenko.testnetty.handler;

import com.jakimenko.testnetty.domain.Entity;
import com.jakimenko.testnetty.exception.BadRequestException;
import com.jakimenko.testnetty.exception.NotFoundException;
import com.jakimenko.testnetty.handler.EntityHandler;
import com.jakimenko.testnetty.service.IService;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.List;
import java.util.Map;

/**
 * @author konst
 */
public abstract class DeleteHandler<T extends Entity> extends AbstractHandler {

    private final IService<T> service;

    public DeleteHandler(IService<T> service) {
        this.service = service;
    }

    @Override
    public Object handleRequest(FullHttpRequest request) throws Exception {
        Map<String, List<String>> parameters = parameters(request);
        if (!parameters.isEmpty()) {
            throw new BadRequestException();
        }

        try {
            int id = fetchId(request.uri());
            return service.delete(id);
        } catch (BadRequestException e) {
            throw new NotFoundException();
        }
    }
}
