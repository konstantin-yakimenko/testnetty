package com.jakimenko.testnetty.exception;

import io.netty.handler.codec.http.HttpResponseStatus;

/**
 * @author konst
 */
public class MethodNotAllowedException extends BaseException {

    public static final HttpResponseStatus STATUS = HttpResponseStatus.METHOD_NOT_ALLOWED;

    public MethodNotAllowedException() {
        super(STATUS, STATUS.reasonPhrase());
    }
}
