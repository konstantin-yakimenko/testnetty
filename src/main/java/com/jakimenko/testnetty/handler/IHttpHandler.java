package com.jakimenko.testnetty.handler;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author konst
 */
public interface IHttpHandler {
    Object handleRequest(FullHttpRequest request) throws Exception;
}
