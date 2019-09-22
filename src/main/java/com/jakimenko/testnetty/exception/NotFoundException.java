package com.jakimenko.testnetty.exception;

import io.netty.handler.codec.http.HttpResponseStatus;

/**
 * @author konst
 */
public class NotFoundException extends BaseException {

    public static final HttpResponseStatus STATUS = HttpResponseStatus.NOT_FOUND;

    public NotFoundException() {
        super(STATUS, STATUS.reasonPhrase());
    }
}
