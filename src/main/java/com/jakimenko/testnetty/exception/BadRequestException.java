package com.jakimenko.testnetty.exception;

import io.netty.handler.codec.http.HttpResponseStatus;

/**
 * @author konst
 */
public class BadRequestException extends BaseException {

    private static final HttpResponseStatus STATUS = HttpResponseStatus.BAD_REQUEST;

    public BadRequestException() {
        super(HttpResponseStatus.BAD_REQUEST, STATUS.reasonPhrase());
    }

    public BadRequestException(String message) {
        super(HttpResponseStatus.BAD_REQUEST, message);
    }
}
