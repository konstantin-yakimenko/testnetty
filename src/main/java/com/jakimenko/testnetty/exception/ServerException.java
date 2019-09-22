package com.jakimenko.testnetty.exception;

import io.netty.handler.codec.http.HttpResponseStatus;

/**
 * @author konst
 */
public class ServerException extends BaseException {

    public static final HttpResponseStatus INTERNAL_SERVER_ERROR = HttpResponseStatus.INTERNAL_SERVER_ERROR;

    public ServerException(Throwable cause) {
        super(INTERNAL_SERVER_ERROR, cause);
    }
}
