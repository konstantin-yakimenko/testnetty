package com.jakimenko.testnetty.handler;

import com.jakimenko.testnetty.domain.Entity;
import com.jakimenko.testnetty.service.IService;

import java.util.Map;

/**
 * @author konst
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class EntityHandler<T extends Entity> extends AbstractHandler {

    protected final IService<T> service;

    protected EntityHandler(IService<T> service) {
        this.service = service;
    }

    protected abstract T parseJson(Map<String, String> map);
}
